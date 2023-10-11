package com.convector.david_000.convector_valute.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.convector.david_000.convector_valute.R
import com.convector.david_000.convector_valute.ui.theme.TrackerTheme

typealias OnClickFunction = () -> Unit

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int, onClick: OnClickFunction
) {
    Button(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .shadow(4.dp, RoundedCornerShape(20.dp)),
        onClick = onClick
    ) {
        Text(
            stringResource(id = text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary
            ),
        )
    }
}

@Composable
fun AppButtonPreview() {
    TrackerTheme {
        AppButton(text = R.string.next) {}
    }
}