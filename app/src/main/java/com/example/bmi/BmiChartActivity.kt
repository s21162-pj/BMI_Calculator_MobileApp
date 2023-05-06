import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.example.bmi.R
class BmiChartActivity : AppCompatActivity() {
private lateinit var mChart: LineChart

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart_bmi)

        mChart = findViewById(R.id.chartBmi)
        mChart.isDragEnabled = true
        mChart.setScaleEnabled(true)

        val entries = ArrayList<Entry>()
        entries.add(Entry(0f, 23.0f))
        entries.add(Entry(1f, 23.3f))
        entries.add(Entry(2f, 23.5f))
        entries.add(Entry(3f, 24.0f))
        entries.add(Entry(4f, 24.3f))
        entries.add(Entry(5f, 23.9f))

        val dataSet = LineDataSet(entries, "Twoje BMI")
        dataSet.color = Color.RED
        dataSet.lineWidth = 2f
        dataSet.circleRadius = 4f
        val lineData = LineData(dataSet)
        mChart.data = lineData

        val description = Description()
        description.text = "Zmiany BMI w odstępach czasu"
        mChart.description = description

        mChart.invalidate()
        }
        }