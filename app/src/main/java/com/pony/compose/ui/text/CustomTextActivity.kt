package com.pony.compose.ui.text

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pony.compose.base.BaseActivity

/**
 *Created by pony on 2022/6/15
 *Description->自定义Text
 */
class CustomTextActivity : BaseActivity() {

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        CustomTextComponent()
    }

    override fun requireTitle(): String  = "Compose Custom Text"
}

@Composable
private fun CustomTextComponent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        SampleText()
        TextWithColor()
        TextWithBiggerFontSize()
        BoldText()
        ItalicText()
        TextWithCustomFontFamily()
        TextWithUnderline()
        TextWithStrikeThrough()
        TextWithSingleLine()
        TextWithShadow()
        CenterTextAlign()
        JustifyTextAlign()
        ModifiedTextIntent()
        ModifiedTextWithLineHeight()
        CustomAnnotatedText()
        CustomClickableText()
    }
}

@Composable
private fun CustomStyledText(displayText: String, style: TextStyle? = null, maxLines: Int? = null) {
    Text(
        text = displayText,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        style = style ?: LocalTextStyle.current,
        overflow = TextOverflow.Ellipsis,
        maxLines = maxLines ?: Int.MAX_VALUE
    )
    Divider(color = Color.DarkGray)
}

//简易文本
@Composable
private fun SampleText() {
    CustomStyledText(displayText = "This is the default text style")
}

//字体颜色
@Composable
private fun TextWithColor() {
    CustomStyledText(displayText = "This text is blue in color", style = TextStyle(color = Color.Blue))
}

//字体大小
@Composable
private fun TextWithBiggerFontSize() {
    CustomStyledText(displayText = "This text has a bigger font size", style = TextStyle(fontSize = 30.sp))
}

//加粗字体
@Composable
private fun BoldText() {
    CustomStyledText(displayText = "This text is bold", style = TextStyle(fontWeight = FontWeight.W700))
}

//斜体字
@Composable
private fun ItalicText() {
    CustomStyledText(displayText = "This text is italic", style = TextStyle(fontStyle = FontStyle.Italic))
}

//字库
@Composable
private fun TextWithCustomFontFamily() {
    CustomStyledText(displayText = "This text is using a custom font family", style = TextStyle(fontFamily = FontFamily.Cursive))
}

//字体带下划线
@Composable
private fun TextWithUnderline() {
    CustomStyledText(displayText = "This text has an underline", style = TextStyle(textDecoration = TextDecoration.Underline))
}

//文字带删除线
@Composable
private fun TextWithStrikeThrough() {
    CustomStyledText(displayText = "This text has a strikethrough line", style = TextStyle(textDecoration = TextDecoration.LineThrough))
}

//文本多出一行显示省略号
@Composable
private fun TextWithSingleLine() {
    CustomStyledText(displayText = "This text will add an ellipsis to the end of the text if the text is longer that 1 line long.", maxLines = 1)
}

//文字带阴影
@Composable
private fun TextWithShadow() {
    CustomStyledText(
        displayText = "This text has a shadow",
        style = TextStyle(shadow = Shadow(color = Color.Red, blurRadius = 10f, offset = Offset(2f, 2f)))
    )
}

//文字居中显示
@Composable
private fun CenterTextAlign() {
    Text(
        text = "This text is center aligned",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        textAlign = TextAlign.Center
    )
    Divider(color = Color.DarkGray)
}

//此文本将演示如何证明您的段落，以确保以软换行符结尾的文本扩展并占据容器的整个宽度
@Composable
private fun JustifyTextAlign() {
    CustomStyledText(
        displayText = "This text will demonstrate how to justify " +
                "your paragraph to ensure that the text that ends with a soft " +
                "line break spreads and takes the entire width of the container", style = TextStyle(textAlign = TextAlign.Justify)
    )
}

//文本缩进
@Composable
private fun ModifiedTextIntent() {
    CustomStyledText(
        displayText = "This text will demonstrate how to add " +
                "indentation to your text. In this example, indentation was only " +
                "added to the first line. You also have the option to add " +
                "indentation to the rest of the lines if you'd like", style = TextStyle(
            textAlign = TextAlign.Justify,
            textIndent = TextIndent(firstLine = 30.sp)
        )
    )
}

//文本间距
@Composable
private fun ModifiedTextWithLineHeight() {
    CustomStyledText(
        displayText = "The line height of this text has been " +
                "increased hence you will be able to see more space between each " +
                "line in this paragraph.", style = TextStyle(lineHeight = 20.sp, textAlign = TextAlign.Justify)
    )
}

//文本多样式
@Composable
private fun CustomAnnotatedText() {
    val annotatedString = buildAnnotatedString {
        append("我是一个多样式文本")
        addStyle(style = SpanStyle(color = Color.Red), start = 0, end = 1)
        addStyle(style = SpanStyle(color = Color.Green), start = 1, end = 2)
        addStyle(style = SpanStyle(color = Color.Cyan), start = 3, end = length)
    }
    Text(text = annotatedString, modifier = Modifier.padding(16.dp))
    Divider(color = Color.DarkGray)
}

//可点击的文本
@Composable
private fun CustomClickableText() {
    val context = LocalContext.current
    val annotatedText = buildAnnotatedString {
        append("please click")
        pushStringAnnotation("url", "https://developer.android.com")
        withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
            append("Android Developer")
        }
    }
    ClickableText(text = annotatedText, onClick = { offset ->
        annotatedText.getStringAnnotations(tag = "url", start = offset, end = offset).firstOrNull()?.let {
            Toast.makeText(context, it.item, Toast.LENGTH_LONG).show()
        }
    }, modifier = Modifier.padding(16.dp))
}
