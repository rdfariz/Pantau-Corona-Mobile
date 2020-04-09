package org.d3if4127.pantaucorona.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.d3if4127.pantaucorona.R
import org.d3if4127.pantaucorona.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass.
 */
class homeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeVM: HomeViewModel

    private var job = Job()
    private val crScope = CoroutineScope(job + Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeVM = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.homeVM = homeVM

        homeVM.indonesia.observe(viewLifecycleOwner, Observer {
            val name = it.get(0).name
            val positif = it.get(0).positif
            val meninggal = it.get(0).meninggal

            binding.tvName.text = "Negara: $name"
            binding.tvPositif.text = "Positif: $positif"
            binding.tvMeninggal.text = "Meninggal: $meninggal"
            Log.i("tesVM", it.toString())
        })

        homeVM.loadingRefresh.observe(viewLifecycleOwner, Observer {
            binding.itemsswipetorefresh.isRefreshing = it
        })
        binding.itemsswipetorefresh.setColorSchemeResources(R.color.colorPrimary)
        binding.itemsswipetorefresh.setOnRefreshListener {
            _handleRefresh()
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    fun _handleRefresh() {
        crScope.launch {
            homeVM.refreshData()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.to_version_web -> {
                val url = "https://pantau-corona.now.sh/"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
            R.id.to_about_app -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

}
