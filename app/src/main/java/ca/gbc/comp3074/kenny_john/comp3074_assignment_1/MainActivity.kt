package ca.gbc.comp3074.kenny_john.comp3074_assignment_1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.gbc.comp3074.kenny_john.comp3074_assignment_1.ui.theme.COMP3074_Assignment1Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            COMP3074_Assignment1Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Pay Calculator") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.LightGray
                            ),
                            actions = {
                                Button(onClick = {
                                    val intent = Intent(
                                        this@MainActivity,
                                        AboutActivity::class.java
                                    )
                                    startActivity(intent)
                                })
                                {
                                    Text("About")
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    CalculatePay(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Composable
fun CalculatePay(modifier: Modifier) {

    Column(modifier = modifier.fillMaxWidth()){
        var hoursWorkedText by remember { mutableStateOf("") }
        var hourlyRateText by remember { mutableStateOf("") }
        var taxRateText by remember { mutableStateOf("") }

        var payText by remember { mutableStateOf("-----") }
        var overtimePayText by remember { mutableStateOf("-----") }
        var totalPayText by remember { mutableStateOf("-----") }
        var taxPayText by remember { mutableStateOf("-----") }

        var resultText by remember { mutableStateOf("Output:") }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            value = hoursWorkedText,
            onValueChange = { hoursWorkedText = it },
            label = { Text("Number of Hours Worked") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            value = hourlyRateText,
            onValueChange = { hourlyRateText = it },
            label = { Text("Hourly Rate") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            value = taxRateText,
            onValueChange = { taxRateText = it },
            label = { Text("Tax Rate") }
        )

        Button(
            onClick = {
                val hoursWorked = hoursWorkedText.toDoubleOrNull()
                val hourlyRate = hourlyRateText.toDoubleOrNull()
                val taxRate = taxRateText.toDoubleOrNull()
                var pay = 0.0
                var overtimePay = 0.0
                var totalPay = 0.0
                var taxPay = 0.0

                if (hoursWorked == null || hourlyRate == null || taxRate == null) {
                    resultText = "Invalid input. Please Try again."
                    payText = "-----"
                    overtimePayText = "-----"
                    totalPayText = "-----"
                    taxPayText = "-----"
                    return@Button
                } else if (hoursWorked <= 40) {
                    pay = hoursWorked * hourlyRate
                } else {
                    pay = 40*hourlyRate
                    overtimePay = (hoursWorked - 40)*hourlyRate*1.5
                }

                totalPay = pay + overtimePay
                taxPay = totalPay * taxRate!!

                resultText = "Output:"
                payText = String.format("Non-overtime pay: $%.2f", pay)
                overtimePayText = String.format("Overtime pay: $%.2f", overtimePay)
                totalPayText = String.format("Total pay: $%.2f", totalPay)
                taxPayText = String.format("Tax paid: $%.2f", taxPay)

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate")
        }
        Text(resultText)
        Text(payText)
        Text(overtimePayText)
        Text(totalPayText)
        Text(taxPayText)



    }



}

@Preview(showBackground = true)
@Composable
fun CalculatePayPreview() {
    COMP3074_Assignment1Theme {
        CalculatePay(modifier = Modifier.padding())
    }
}
