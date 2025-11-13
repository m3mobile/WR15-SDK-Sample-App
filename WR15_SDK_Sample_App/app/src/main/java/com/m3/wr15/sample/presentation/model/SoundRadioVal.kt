package com.m3.wr15.sample.presentation.model

import com.m3.wr15.sdk.api.model.TypeValue
import com.m3.wr15.sdk.api.model.general.SoundType

enum class SoundRadioVal(
    override val label: String,
    override val value: TypeValue
) : SettingRadioValue {
    ON(
        "ON",
        value = SoundType.ON,
    ),
    MUTE_ON_FAILED_SCAN(
        "MUTE ON FAILED SCAN",
        value = SoundType.MUTE_ON_FAILED_SCAN,
    ),
    OFF(
        "OFF",
        value = SoundType.OFF,
    );

    companion object {
        fun getList(): List<SoundRadioVal> {
            return entries
        }

        fun fromBarcodeValue(value: SoundType?): SoundRadioVal? {
            return entries.find { it.value == value }
        }
    }
}