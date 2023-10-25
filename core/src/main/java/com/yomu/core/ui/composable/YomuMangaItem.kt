package com.yomu.core.ui.composable

import android.graphics.Shader
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.yomu.core.R
import com.yomu.core.ext.fontDimensionResource
import com.yomu.core.model.YomuMangaModel

@Composable
fun YomuMangaItem(item: YomuMangaModel) {
    Box(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.manga_item_padding))
            .fillMaxWidth()
            .height(170.dp)
            .clip(shape = RoundedCornerShape(dimensionResource(id = R.dimen.manga_item_corner)))
    ) {
        AsyncImage(
            model = item.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawGradient(startYPercent = 0.5f)
        )
        Text(
            text = item.name,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(dimensionResource(id = R.dimen.manga_item_text_padding)),
            style = TextStyle(
                color = Color.White,
                fontSize = fontDimensionResource(R.dimen.manga_item_text_size),
                fontWeight = FontWeight.Medium
            )
        )
    }
}

fun Modifier.drawGradient(startYPercent: Float): Modifier = this.then(
    Modifier.drawWithContent {
        this.drawContent()
        drawIntoCanvas { canvas ->
            val paint = Paint().apply {
                shader = android.graphics.LinearGradient(
                    0f,
                    size.height * startYPercent,
                    0f,
                    size.height,
                    intArrayOf(
                        Color.Transparent.toArgb(),
                        Color.Black.copy(alpha = 0.8f).toArgb()
                    ),
                    floatArrayOf(0f, 1f),
                    Shader.TileMode.CLAMP
                )
            }
            canvas.drawRect(
                Rect(
                    top = size.height * startYPercent,
                    left = 0f,
                    right = size.width,
                    bottom = size.height
                ),
                paint
            )
        }
    }
)





