import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lounge.R
import com.example.lounge.ui.theme.LoungePurple
import com.example.lounge.ui.theme.WhiteText
import com.example.lounge.ui.theme.components.ItemCard

//@Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Logo Section
            HeaderSection()
//
            Spacer(modifier = Modifier.height(200.dp))

            // Button Section
            ContentSection(navController)
        }
    }
}

@Composable
fun HeaderSection() {
    // Imagem do logo
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Lounge do Açaí",
        modifier = Modifier
            .fillMaxWidth()
            .height(504.dp),
        contentScale = ContentScale.Crop
    )
}



@Composable
fun ContentSection(navController: NavHostController) {
    // Botão "Realizar pedido"
    Button(
        onClick = { navController
            .navigate("chooseAProduct")},
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(112.dp),
        colors = ButtonDefaults.buttonColors(containerColor = LoungePurple),
        shape = RoundedCornerShape(12.dp) // Botão com bordas arredondadas
    ) {
        Text(
            text = "Realizar pedido",
            color = WhiteText,
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold
        )
    }
}