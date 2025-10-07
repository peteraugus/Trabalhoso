package com.example.jmvsprojeto.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.jmvsprojeto.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jmvsprojeto.data.AppDatabase
import com.example.jmvsprojeto.databinding.FragmentListBinding
import kotlinx.coroutines.launch

class ProductListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.btnAdd.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProductFormFragment())
                .addToBackStack(null)
                .commit()
        }

        loadData()
        return binding.root
    }

    private fun loadData() {
        val db = AppDatabase.getDatabase(requireContext())
        lifecycleScope.launch {
            val products = db.productDao().getAll()
            binding.recyclerView.adapter = SimpleAdapter(products.map { it.name })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
