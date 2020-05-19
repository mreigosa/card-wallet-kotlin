package com.mreigar.cardwalletapp.cardlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mreigar.cardwalletapp.R
import com.mreigar.cardwalletapp.gone
import com.mreigar.cardwalletapp.visible
import kotlinx.android.synthetic.main.fragment_card_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class CardListFragment : Fragment() {

    private val viewModel: CardListViewModel by viewModel()

    private val cardAdapter = CardAdapter {
        findNavController().navigate(CardListFragmentDirections.actionCardDetails(it))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_card_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardAddButton.setOnClickListener {
            findNavController().navigate(CardListFragmentDirections.actionAddCard())
        }

        cardList.apply {
            adapter = cardAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.state.observe(viewLifecycleOwner, Observer {
            if (it.isLoading) {
                cardListLoader.visible()
            } else {
                cardListLoader.gone()
                if (it.cards.isEmpty()) {
                    cardEmptyText.visible()
                } else {
                    cardEmptyText.gone()
                    cardAdapter.setCards(it.cards)
                }
            }
        })

        viewModel.loadWallet()
    }

}