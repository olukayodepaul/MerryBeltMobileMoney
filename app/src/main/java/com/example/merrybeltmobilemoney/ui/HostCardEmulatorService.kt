package com.example.merrybeltmobilemoney.ui

import android.nfc.cardemulation.HostApduService
import android.os.Bundle

class HostCardEmulatorService: HostApduService() {

    override fun processCommandApdu(p0: ByteArray?, p1: Bundle?): ByteArray {
        TODO("Not yet implemented")
    }

    override fun onDeactivated(p0: Int) {
        TODO("Not yet implemented")
    }
}