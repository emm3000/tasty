package com.emm.tasty.fragments.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.emm.tasty.R
import com.emm.tasty.constants.Constants.DEFAULT_LATITUDE
import com.emm.tasty.constants.Constants.DEFAULT_LONGITUDE
import com.emm.tasty.databinding.FragmentMapBinding
import com.emm.tasty.utils.safeCollect
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import com.google.maps.android.ktx.awaitMapLoad
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MapFragment : Fragment(), OnMarkerClickListener {

    private val args: MapFragmentArgs by navArgs()
    private lateinit var binding: FragmentMapBinding
    private lateinit var mMap: GoogleMap
    private val viewModel: MapViewModel by viewModel(
        parameters = { parametersOf(args.keyword) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        binding.mapViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectUseCase()
        setupGoogleMap()
    }

    private fun collectUseCase() = safeCollect {
        viewModel.mapHomeViewState.collect {
            addMarkers(it)
        }
    }

    private fun setupGoogleMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            val googleMap: GoogleMap = mapFragment.awaitMap()
            mMap = googleMap
            val limaCoordinates = LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(limaCoordinates, 14f))
            mMap.setOnMarkerClickListener(this@MapFragment)
            mMap.awaitMapLoad()
        }
    }

    private fun addMarkers(places: MapViewState) {
        places.places?.forEach {
            mMap.addMarker {
                position(LatLng(it.latitude, it.longitude))
                title(it.name)
            }.apply { tag = it.placeId }
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val tag = marker.tag as? String
        viewModel.findPlaceOnMarkerSelected(tag.orEmpty())
        return false
    }

}