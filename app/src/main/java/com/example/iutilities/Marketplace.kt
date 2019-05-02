package com.example.iutilities

import android.content.ClipData
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_marketplace.*
import kotlinx.android.synthetic.main.cardview.view.*

class Marketplace : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)

        sell_button_sell.setOnClickListener {
            val intent = Intent(this, Sell::class.java)
            startActivity(intent)
        }

        fetchitems("Tech")
    }

    private fun fetchitems(category: String)
    {
        val adapter = GroupAdapter<ViewHolder>()
        val ref = FirebaseDatabase.getInstance().getReference("/sell/${category.toString()}")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach{
                    val item_tmp = it.getValue(ItemObj::class.java)
                    if ( item_tmp != null )
                    {
                        adapter.add(itemholder(item_tmp))
                    }
                }
                item_recyclerview.adapter = adapter
            }
        })
    }

}

class itemholder(val item: ItemObj): Item<ViewHolder>()
{
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.itemName.setText("${item?.name.toString()}")
        viewHolder.itemView.itemPrice.setText("${item?.price.toString()}")
    }

    override fun getLayout(): Int {
        return R.layout.cardview
    }
}

