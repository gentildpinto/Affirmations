package me.gentilpinto.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.gentilpinto.affirmations.data.DataSource
import me.gentilpinto.affirmations.model.Affirmation
import me.gentilpinto.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp()
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AffirmationAppPreview() {
    AffirmationsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            AffirmationsApp()
        }
    }
}

@Composable
fun AffirmationsApp() {
    AffirmationList(affirmationList = DataSource.loadAffirmations())
}

@Composable
fun AffirmationList(
    affirmationList: List<Affirmation>, modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(affirmationList) {
            AffirmationCard(affirmation = it, modifier = Modifier.padding(8.dp))
        }
    }
}


@Composable
fun AffirmationCard(
    affirmation: Affirmation, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Image(
            painter = painterResource(affirmation.imageResourceId),
            contentDescription = stringResource(
                affirmation.stringResourceId
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(194.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(affirmation.stringResourceId),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}