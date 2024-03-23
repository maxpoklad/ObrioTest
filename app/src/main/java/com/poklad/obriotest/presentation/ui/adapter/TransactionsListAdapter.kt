package com.poklad.obriotest.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.poklad.obriotest.databinding.ItemTransactionBinding
import com.poklad.obriotest.presentation.model.TransactionPresentationModel
import com.poklad.obriotest.utils.DateConverter

class TransactionsListAdapter(private val dataConverter: DateConverter): PagingDataAdapter<TransactionPresentationModel, TransactionsListAdapter.TransactionViewHolder>(TRANSACTION_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TransactionViewHolder(ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = getItem(position)
        holder.bind(transaction)
    }

    inner class TransactionViewHolder(private val binder: ItemTransactionBinding) : RecyclerView.ViewHolder(binder.root) {
        fun bind(transaction: TransactionPresentationModel?) {
            binder.tvTransaction.text = transaction?.destination
            binder.tvTransactionDate.text = transaction?.time?.let(dataConverter::convertLongToDateTime)
            binder.tvFullAmount.text = transaction?.totalAmount.toString()
            binder.tvTransactionAmount.text = transaction?.transactionAmount
                .toString()
                .let { if (transaction?.isDeposit == true) "+$it" else "-$it" }
        }
    }

    companion object {
        private val TRANSACTION_COMPARATOR = object : DiffUtil.ItemCallback<TransactionPresentationModel>() {
            override fun areItemsTheSame(oldItem: TransactionPresentationModel, newItem: TransactionPresentationModel): Boolean {
                return oldItem.time == newItem.time && oldItem.coin == newItem.coin
            }
            override fun areContentsTheSame(oldItem: TransactionPresentationModel, newItem: TransactionPresentationModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}