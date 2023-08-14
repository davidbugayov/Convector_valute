package com.convector.david_000.convector_valute.model


sealed class RZDAction {
    object OpenEditAddressPart : RZDAction()

    //
    data class SymbolsList(val symbolsItem: String) : RZDAction()
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
