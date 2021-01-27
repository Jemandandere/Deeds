package com.jemandandere.deeds.screens.deeds_list

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import com.jemandandere.deeds.App
import com.jemandandere.deeds.R
import com.jemandandere.deeds.model.Deed
import kotlinx.android.synthetic.main.deed_item.view.*
import kotlinx.coroutines.*

class DeedsListAdapter: RecyclerView.Adapter<DeedsListAdapter.DeedsViewHolder>() {

    private val deedList: SortedList<Deed> = SortedList(Deed::class.java, object : SortedList.Callback<Deed>() {
        override fun compare(o1: Deed, o2: Deed): Int {
            if (!o2.done && o1.done) {
                return 1
            }
            if (o2.done && !o1.done) {
                return -1
            }
            return (o2.timestamp - o1.timestamp).toInt()
        }

        override fun areContentsTheSame(oldItem: Deed, newItem: Deed): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areItemsTheSame(item1: Deed, item2: Deed): Boolean {
            return item1.id == item2.id
        }

        override fun onChanged(position: Int, count: Int) {
            notifyItemRangeChanged(position, count)
        }

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position, count);
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeedsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.deed_item, parent, false)
        return DeedsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeedsViewHolder, position: Int) {
        holder.bind(deedList[position])
    }

    override fun onViewAttachedToWindow(holder: DeedsViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onAttach();
    }

    override fun onViewDetachedFromWindow(holder: DeedsViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetach();
    }

    override fun getItemCount(): Int {
        return deedList.size()
    }

    fun setList(list: List<Deed>) {
        deedList.replaceAll(list)
    }

    class DeedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var deed: Deed

        val itemCheckBox: CheckBox = itemView.deed_checkbox

        var isRealUpdate: Boolean = true

        fun bind(deed: Deed) {
            this.deed = deed

            isRealUpdate = false
            itemCheckBox.isChecked = deed.done
            isRealUpdate = true
            itemCheckBox.text = deed.text
            // Необходимо поправить, это избыточно, но без него не работает о0
            updateStrokeOut()
        }

        fun onAttach() {
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("deed", deed)
                App.instance.navController.navigate(R.id.action_deedsListFragment_to_deedEditFragment, bundle)
            }
            itemCheckBox.setOnCheckedChangeListener( object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                    if (isRealUpdate) {
                        deed.done = isChecked
                        // TODO Переделать, мне не нравится вызов корутин из адаптера еще и в GlobalScope
                        GlobalScope.launch(Dispatchers.IO) {
                            App.instance.dbRepository.update(deed, {})
                        }
                    }
                    updateStrokeOut()
                }
            })
        }

        fun onDetach() {
            itemView.setOnClickListener(null)
            itemCheckBox.setOnCheckedChangeListener(null)
        }

        fun updateStrokeOut() {
            if (deed.done) {
                itemCheckBox.paintFlags = itemCheckBox.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                itemCheckBox.paintFlags = itemCheckBox.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }
}