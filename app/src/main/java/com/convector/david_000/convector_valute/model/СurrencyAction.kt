package com.convector.david_000.convector_valute.model

import com.convector.david_000.convector_valute.data.local.SymbolsItem

sealed class СurrencyAction {
    object OpenEditAddressPart : СurrencyAction()

    //
    data class SymbolsList(val symbolsItem: SymbolsItem) : СurrencyAction()
//    data class OpenConnectionRequest(
//        val fttbData: FttbConstructorData,
//        val parts: List<FttbAddressPart>
//    ) : FttbCheckAddressAction()
//
//    data class OpenConnectionImpossible(
//        val data: CheckAddress
//    ) : FttbCheckAddressAction()
//
//    object OpenGenericError : FttbCheckAddressAction()
//
//    object GoBack : FttbCheckAddressAction()
}
