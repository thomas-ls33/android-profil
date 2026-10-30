package com.example.profil

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profil.ui.theme.ProfilTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val email = "thomasou.laas@gmail.com"
        setContent {
            ProfilTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfilScreen(
                        name = "Thomas Laas",
                        role = "Développeur Android junior",
                        description = "Étudiant en informatique, je développe des applications " +
                            "Android avec Kotlin et Jetpack Compose. Je cherche une alternance.",
                        skills = listOf("Kotlin", "Jetpack Compose", "Git"),
                        availability = "Disponible pour une alternance",
                        contact = email,
                        onContactClick = { envoyerEmail(email) },
                        onAvailabilityClick = {},
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun envoyerEmail(adresse: String) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$adresse"))
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Aucune application e-mail installée", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun ProfilScreen(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    availability: String,
    contact: String,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ProfilCard(
            name = name,
            role = role,
            description = description,
            skills = skills,
            availability = availability,
            contact = contact,
            onContactClick = onContactClick,
            onAvailabilityClick = onAvailabilityClick,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ProfilCard(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    availability: String,
    contact: String,
    onContactClick: () -> Unit,
    onAvailabilityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            ProfilHeader(name = name, role = role)

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 12.dp)
            )

            Text(
                text = "Compétences",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 20.dp, bottom = 4.dp)
            )
            skills.forEach { skill ->
                SkillItem(skill = skill)
            }

            AvailabilityZone(
                availability = availability,
                onClick = onAvailabilityClick,
                modifier = Modifier.padding(top = 20.dp)
            )

            InfoSecondaire(
                label = "Contact",
                value = contact,
                modifier = Modifier.padding(top = 12.dp)
            )

            ActionButton(
                text = "Contacter",
                onClick = onContactClick,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun ProfilHeader(
    name: String,
    role: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = role,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun SkillItem(
    skill: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = skill,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun InfoSecondaire(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun AvailabilityZone(
    availability: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = MaterialTheme.colorScheme.tertiaryContainer,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClickLabel = "Voir le détail de la disponibilité",
                role = Role.Button,
                onClick = onClick
            )
            .semantics { contentDescription = "Disponibilité : $availability. Appuyer pour voir le détail." }
    ) {
        Text(
            text = availability,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = text)
    }
}

@Preview(showBackground = true, name = "Android junior disponible")
@Composable
fun ProfilScreenAndroidJuniorPreview() {
    ProfilTheme {
        ProfilScreen(
            name = "Thomas Laas",
            role = "Développeur Android junior",
            description = "Étudiant en informatique, je développe des applications Android " +
                "avec Kotlin et Jetpack Compose. Je cherche une alternance.",
            skills = listOf("Kotlin", "Jetpack Compose", "Git"),
            availability = "Disponible pour une alternance",
            contact = "thomasou.laas@gmail.com",
            onContactClick = {},
            onAvailabilityClick = {}
        )
    }
}

@Preview(showBackground = true, name = "Full-stack non disponible")
@Composable
fun ProfilScreenFullStackPreview() {
    ProfilTheme {
        ProfilScreen(
            name = "Camille Martin",
            role = "Développeuse full-stack",
            description = "Cinq ans d'expérience sur des applications web et mobiles, " +
                "de l'API au front. Actuellement en mission freelance.",
            skills = listOf("TypeScript", "Node.js", "PostgreSQL", "Docker"),
            availability = "Indisponible avant janvier",
            contact = "camille.martin@example.com",
            onContactClick = {},
            onAvailabilityClick = {}
        )
    }
}
