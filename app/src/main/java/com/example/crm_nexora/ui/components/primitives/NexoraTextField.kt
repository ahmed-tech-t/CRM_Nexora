package com.example.crm_nexora.ui.components.primitives

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraPrimaryFocus
import com.example.crm_nexora.ui.theme.NexoraSurfaceFocus
import com.example.crm_nexora.ui.theme.NexoraSurfaceMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary

/**
 * Controlled text field — value/focus/error owned by parent/ViewModel.
 */
@Composable
fun NexoraTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    isPassword: Boolean = false,
    isFocusedStyle: Boolean = false,
    isError: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    trailing: (@Composable () -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    val shape = RoundedCornerShape(NexoraDimens.radiusMd)
    val borderColor = when {
        isError -> MaterialTheme.colorScheme.error
        isFocusedStyle -> NexoraPrimaryFocus
        else -> NexoraBorder
    }
    val borderWidth = if (isFocusedStyle || isError) NexoraDimens.borderFocus else NexoraDimens.borderThin
    val container = if (isFocusedStyle) NexoraSurfaceFocus else NexoraSurfaceMuted

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceXs),
    ) {
        if (!label.isNullOrBlank()) {
            NexoraLabel(text = label)
        }
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .border(borderWidth, borderColor, shape),
            enabled = enabled,
            singleLine = singleLine,
            textStyle = MaterialTheme.typography.titleSmall.copy(
                color = NexoraTextPrimary,
                textAlign = TextAlign.Start,
            ),
            placeholder = placeholder?.let {
                {
                    NexoraText(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
            trailingIcon = trailing,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            shape = shape,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = container,
                unfocusedContainerColor = container,
                disabledContainerColor = container,
                errorContainerColor = container,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            isError = isError,
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun NexoraTextFieldPreview() {
    CRM_NexoraTheme {
        Column(verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd)) {
            NexoraTextField(
                value = "9999",
                onValueChange = {},
                label = "كود الشركة",
                isFocusedStyle = true,
            )
            NexoraTextField(
                value = "ahmed.medrep",
                onValueChange = {},
                label = "اسم المستخدم",
            )
        }
    }
}
