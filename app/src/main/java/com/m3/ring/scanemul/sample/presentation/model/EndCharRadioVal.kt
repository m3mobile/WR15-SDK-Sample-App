package com.m3.ring.scanemul.sample.presentation.model

import com.m3.ring.scanemul.sdk.api.model.TypeValue
import com.m3.ring.scanemul.sdk.api.model.settings.basic.BasicFormatType

enum class EndCharRadioVal(
    override val label: String,
    override val value: TypeValue,
) : SettingRadioValue {
    ENTER("ENTER", BasicFormatType.EndCharType.ENTER),
    TAB("TAB", BasicFormatType.EndCharType.TAB),
    NONE("NONE", BasicFormatType.EndCharType.NONE);

    companion object {
        fun getList(): List<EndCharRadioVal> =
            EndCharRadioVal.entries

        fun fromBarcodeValue(value: TypeValue?): EndCharRadioVal? =
            EndCharRadioVal.entries.find { it.value == value }
    }
}