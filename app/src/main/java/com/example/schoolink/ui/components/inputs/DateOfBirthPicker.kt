import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun DateOfBirthPicker(onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    var dateText by remember { mutableStateOf("Select Date") }

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            dateText = "$year-${month + 1}-$dayOfMonth"
            onDateSelected(dateText)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column {
        Text(
            text = dateText,
            modifier = androidx.compose.ui.Modifier.clickable { datePickerDialog.show() }
        )
    }
}
