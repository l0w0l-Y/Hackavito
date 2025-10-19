package ru.aleksandra.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.check_will_be_sent_to_this_address
import hackavito.core.ui.generated.resources.courier
import hackavito.core.ui.generated.resources.full_name
import hackavito.core.ui.generated.resources.ic_cross
import hackavito.core.ui.generated.resources.ic_delete
import hackavito.core.ui.generated.resources.mail
import hackavito.core.ui.generated.resources.phone
import hackavito.core.ui.generated.resources.recipient
import hackavito.core.ui.generated.resources.save_recipient
import hackavito.core.ui.generated.resources.we_will_send_the_order_pickup_code_to_this_number
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.aleksandra.core.theme.bgElevation1
import ru.aleksandra.core.theme.bgElevation2
import ru.aleksandra.core.theme.bgPage
import ru.aleksandra.core.theme.black
import ru.aleksandra.core.theme.buttonBgPrimary
import ru.aleksandra.core.theme.buttonTextPrimary
import ru.aleksandra.core.theme.buttonTextSecondary
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.controlBgDefault
import ru.aleksandra.core.theme.controlTextPrimary
import ru.aleksandra.core.theme.controlTextSecondary
import ru.aleksandra.core.theme.gray92
import ru.aleksandra.core.theme.h30
import ru.aleksandra.core.theme.h50
import ru.aleksandra.core.theme.m20
import ru.aleksandra.core.theme.s10
import ru.aleksandra.core.theme.warmgray4
import ru.aleksandra.core.theme.white
import ru.aleksandra.core.ui.model.Recipient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvitoDeliveryRecipientBottomSheet(
    onDismiss: () -> Unit,
    onSave: (name: String, number: String, email: String) -> Unit,
    recipient: Recipient

) {
    var name by remember { mutableStateOf(TextFieldValue(recipient.name)) }
    var number by remember { mutableStateOf(TextFieldValue(recipient.number)) }
    var email by remember { mutableStateOf(TextFieldValue(recipient.email)) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
        containerColor = MaterialTheme.colorScheme.bgElevation1
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 12.dp)
            ) {
                Text(
                    text = stringResource(Res.string.recipient),
                    style = MaterialTheme.typography.h30,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = MaterialTheme.colorScheme.contentPrimary,
                )
                Text(
                    text = stringResource(Res.string.full_name),
                    style = MaterialTheme.typography.h50,
                    color = MaterialTheme.colorScheme.controlTextPrimary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    trailingIcon = {
                        if (name.text.isNotEmpty()) {
                            IconButton(onClick = { name = TextFieldValue("") }) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_cross),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.buttonTextSecondary,
                                    modifier = Modifier.size(height = 20.dp, width = 20.dp)
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.controlBgDefault,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                    ),
                )
                Text(
                    text = stringResource(Res.string.phone),
                    style = MaterialTheme.typography.h50,
                    color = MaterialTheme.colorScheme.controlTextPrimary,
                    modifier = Modifier.padding(top = 16.dp, bottom = 12.dp)
                )

                // Поле Телефон
                OutlinedTextField(
                    textStyle = MaterialTheme.typography.m20,
//                textColor = MaterialTheme.colorScheme.controlTextPrimary,

                    value = number,
                    onValueChange = { number = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    trailingIcon = {
                        if (number.text.isNotEmpty()) {
                            IconButton(onClick = { number = TextFieldValue("") }) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_cross),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.buttonTextSecondary,
                                    modifier = Modifier.size(height = 20.dp, width = 20.dp)
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.controlBgDefault,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                    ),
                )
                Text(
                    text = stringResource(Res.string.we_will_send_the_order_pickup_code_to_this_number),
                    style = MaterialTheme.typography.s10,
                    color = MaterialTheme.colorScheme.controlTextSecondary,
                    modifier = Modifier.padding(top = 6.dp, bottom = 18.dp)
                )

                Text(
                    text = stringResource(Res.string.mail),
                    style = MaterialTheme.typography.h50,
                    color = MaterialTheme.colorScheme.controlTextPrimary,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Поле Почта
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    trailingIcon = {
                        if (email.text.isNotEmpty()) {
                            IconButton(onClick = { email = TextFieldValue("") }) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_cross),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.buttonTextSecondary,
                                    modifier = Modifier.size(height = 20.dp, width = 20.dp)
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.controlBgDefault,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                    ),
                )
                Text(
                    text = stringResource(Res.string.check_will_be_sent_to_this_address),
                    style = MaterialTheme.typography.s10,
                    color = MaterialTheme.colorScheme.controlTextSecondary,
                    modifier = Modifier.padding(top = 6.dp, bottom = 16.dp)
                )

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.bgElevation2,
                        shape = RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .border(
                        0.dp,
                        color = MaterialTheme.colorScheme.contentPrimary,
                        shape = RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
                    .padding(start = 10.dp, top = 10.dp, end = 10.dp),
            ) {
                Button(
                    onClick = {
                        onSave(name.text, number.text, email.text)
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.buttonBgPrimary,
                    ),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues(
                        top = 15.dp,
                        bottom = 17.dp,

                        ),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(Res.string.save_recipient),
                        style = MaterialTheme.typography.m20,
                        color = MaterialTheme.colorScheme.buttonTextPrimary,
                    )
                }
            }

        }
    }
}