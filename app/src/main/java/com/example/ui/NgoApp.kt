package com.example.ui

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.*
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

// Simple Localization Translator
object Translator {
    private val dict = mapOf(
        "EN" to mapOf(
            "app_subtitle" to "NGO Coordination & Global Operations",
            "dashboard" to "Analytics Dashboard",
            "field_ops" to "MD/Staff Workspace",
            "volunteer" to "Volunteer Hub",
            "donations" to "Donations & Reports",
            "security" to "Security & IAM",
            "role" to "Active Session Role",
            "language" to "System Language",
            "total_budget" to "Total Program Budget",
            "total_spent" to "Total Budget Spent",
            "fund_efficiency" to "Fund Outflow Efficiency",
            "active_projects" to "Active Global Projects",
            "volunteers_deployed" to "Volunteers Deployed",
            "security_integrity" to "Global Cyber Integrity",
            "connected_remotes" to "Connected System Ports",
            "offline_status" to "Offline Cache State",
            "sync_system" to "Local Offline Sync Cache",
            "overdue_tasks" to "Overdue Deadline Breaches",
            "project_velocity_chart" to "Cross-Regional Project Velocity Metrics (Canvas Graphics)",
            "add_project" to "Launch New Project Cluster",
            "project_details" to "Project Details & Active Webhook Integrations",
            "slack_integration" to "Slack Integrations Log Stream",
            "volunteer_join_title" to "Become a Sarathi - Join NGO Portal",
            "submit_application" to "Submit Security-Cleared Profile",
            "track_donations_title" to "Automated Donation Verification & Ledger",
            "record_donation" to "Record Secure Instant Donation Ledger Entry",
            "download_reports" to "Transparent Stakeholder Reporting Tools",
            "compliance_audit" to "Data Privacy & IAM Compliance Audit Logs"
        ),
        "HI" to mapOf(
            "app_subtitle" to "गैर-लाभकारी संगठन वैश्विक संचालन और समन्वय",
            "dashboard" to "डैशबोर्ड विश्लेषण",
            "field_ops" to "एमडी/कर्मचारी कार्यक्षेत्र",
            "volunteer" to "स्वयंसेवक केंद्र",
            "donations" to "दान और पारदर्शी रिपोर्ट",
            "security" to "सुरक्षा और आईएएम",
            "role" to "सक्रिय सत्र भूमिका",
            "language" to "सिस्टम भाषा",
            "total_budget" to "कुल कार्यक्रम बजट",
            "total_spent" to "कुल व्यय किया गया बजट",
            "fund_efficiency" to "निधि उपयोग दक्षता",
            "active_projects" to "सक्रिय वैश्विक परियोजनाएं",
            "volunteers_deployed" to "तैनात स्वयंसेवक",
            "security_integrity" to "वैश्विक साइबर अखंडता",
            "connected_remotes" to "कनेक्टेड दूरस्थ सिस्टम",
            "offline_status" to "ऑफ़लाइन कैश स्थिति",
            "sync_system" to "स्थानीय ऑफ़लाइन सिंक संचय",
            "overdue_tasks" to "समय सीमा उल्लंघन मामले",
            "project_velocity_chart" to "क्रॉस-क्षेत्रीय परियोजना गति मेट्रिक्स (कैनवास ग्राफिक्स)",
            "add_project" to "नई परियोजना शुरू करें",
            "project_details" to "परियोजना विवरण और लाइव एकीकरण लॉग",
            "slack_integration" to "स्लैक इंटीग्रेशन लॉग",
            "volunteer_join_title" to "सारथी बनें - स्वयंसेवक केंद्र",
            "submit_application" to "प्रोफ़ाइल सबमिट करें",
            "track_donations_title" to "स्वचालित सुरक्षा दान सत्यापन बहीखाता",
            "record_donation" to "तत्काल दान रिकॉर्ड करें",
            "download_reports" to "पारदर्शी हितधारक रिपोर्टिंग उपकरण",
            "compliance_audit" to "डेटा गोपनीयता और ऑडिट खाता"
        ),
        "ES" to mapOf(
            "app_subtitle" to "Coordinación y Operaciones Globales de la ONG",
            "dashboard" to "Panel de Analíticas",
            "field_ops" to "Flujo de MD y Personal",
            "volunteer" to "Portal de Voluntarios",
            "donations" to "Donaciones y Reportes",
            "security" to "Seguridad e IAM",
            "role" to "Rol Activo del Sesión",
            "language" to "Idioma del Sistema",
            "total_budget" to "Presupuesto Total de Programas",
            "total_spent" to "Total Presupuesto Transmitido",
            "fund_efficiency" to "Ratio de Fondos Usados",
            "active_projects" to "Proyectos Globales Activos",
            "volunteers_deployed" to "Voluntarios Desplegados",
            "security_integrity" to "Integridad Cibernética Global",
            "connected_remotes" to "Puertos del Sistema Conectados",
            "offline_status" to "Estado de Caché Offline",
            "sync_system" to "Sincronización Offline Local",
            "overdue_tasks" to "Tareas con Plazo Vencido",
            "project_velocity_chart" to "Métricas de Velocidad Regionales (Canvas)",
            "add_project" to "Lanzar Nuevo Proyecto",
            "project_details" to "Detalle de Proyecto y Logs Integrados",
            "slack_integration" to "Mensajes en Slack de NextSarathi",
            "volunteer_join_title" to "Conviértete en Sarathi - Unirse",
            "submit_application" to "Enviar Perfil Verificado de Seguridad",
            "track_donations_title" to "Verificación de Donaciones y Ledger",
            "record_donation" to "Registrar Entrada de Donación Segura",
            "download_reports" to "Herramientas de Informes Transparentes",
            "compliance_audit" to "Registro de Auditoría de Privacidad de Datos"
        ),
        "FR" to mapOf(
            "app_subtitle" to "Coordination des ONG & Opérations Globales",
            "dashboard" to "Tableau de Bord Analytique",
            "field_ops" to "Directeur & Équipe",
            "volunteer" to "Espace Volontaires",
            "donations" to "Dons & Rapports",
            "security" to "Sécurité & IAM",
            "role" to "Rôle Actif de Session",
            "language" to "Langue du Système",
            "total_budget" to "Budget Global des Programmes",
            "total_spent" to "Budget Total Consommé",
            "fund_efficiency" to "Ratio d'Utilisation des Fonds",
            "active_projects" to "Projets Globaux Actifs",
            "volunteers_deployed" to "Volontaires Déployés",
            "security_integrity" to "Intégrité Cybernétique Globale",
            "connected_remotes" to "Ports Systèmes Connectés",
            "offline_status" to "État de la Cache Hors-ligne",
            "sync_system" to "Synchronisation Hors-ligne Locale",
            "overdue_tasks" to "Brèches Limites Dépassées",
            "project_velocity_chart" to "Indicateurs de Vélocité Régionaux (Dessin Canvas)",
            "add_project" to "Lancer un Projet Cluster",
            "project_details" to "Détails du Projet & Webhooks Actifs",
            "slack_integration" to "Flux de Notifications Slack",
            "volunteer_join_title" to "Devenir Sarathi - Rejoindre l'ONG",
            "submit_application" to "Soumettre votre Profil Vérifié",
            "track_donations_title" to "Suivi Automatisé et Ledger des Dons",
            "record_donation" to "Enregistrer une Transaction Sécurisée",
            "download_reports" to "Outils de Rapports Transparent",
            "compliance_audit" to "Grand Livre d'Audit de Protection des Données"
        )
    )

    fun translate(lang: String, key: String): String {
        return dict[lang]?.get(key) ?: key
    }
}

// Beautiful theme colors adapted dynamically for Bento Grid (light & dark mode compliant)
val SlateBg: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF0F172A) else Color(0xFFF6F8FC)

val CardSlate: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF1E293B) else Color(0xFFFFFFFF)

val LightSlateBorder: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF334155) else Color(0xFFE2E8F0)

val CyberGreen = Color(0xFF10B981)
val CyberGreenGlow = Color(0xFF34D399)
val CyberAmber = Color(0xFFF59E0B)
val CyberBlue = Color(0xFF0EA5E9)
val CyberBlueGlow = Color(0xFF38BDF8)

val DarkText: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFF8FAFC) else Color(0xFF1A1C1E)

val LightMutedText: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF94A3B8) else Color(0xFF64748B)

val AlertCrimson = Color(0xFFEF4444)

@Composable
fun NextSarathiAppContent(viewModel: NgoViewModel) {
    val context = LocalContext.current
    val currentTab by viewModel.currentTab.collectAsState()
    val activeRole by viewModel.currentRole.collectAsState()
    val language by viewModel.currentLanguage.collectAsState()
    val isOffline by viewModel.isOfflineMode.collectAsState()
    val syncText by viewModel.syncStatusText.collectAsState()
    
    val projectsList by viewModel.projects.collectAsState()
    val donationsList by viewModel.donations.collectAsState()
    val volunteersList by viewModel.volunteers.collectAsState()
    val auditLogsList by viewModel.auditLogs.collectAsState()

    // Screen shell - Scaffold enforcing full bleeding and modern styling
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = SlateBg,
        topBar = {
            TopAppBarEnsemble(
                viewModel = viewModel,
                isOffline = isOffline,
                syncText = syncText,
                language = language,
                activeRole = activeRole
            )
        },
        bottomBar = {
            BottomNavigationBarEnsemble(
                currentTab = currentTab,
                onTabSelected = { viewModel.setTab(it) },
                language = language
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(SlateBg)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Header details
                AppSubtitleBanner(language)

                Spacer(modifier = Modifier.height(16.dp))

                // Role and Sync Warning Info cards
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    InfoBadgeCard(
                        icon = Icons.Default.VerifiedUser,
                        title = Translator.translate(language, "role"),
                        value = activeRole,
                        color = CyberBlue,
                        modifier = Modifier.weight(1f)
                    )
                    InfoBadgeCard(
                        icon = if (isOffline) Icons.Default.CloudOff else Icons.Default.CloudDone,
                        title = Translator.translate(language, "offline_status"),
                        value = if (isOffline) "OFFLINE MODE ENABLED" else "CLOUD LIVE SYNC ACTIVE",
                        color = if (isOffline) CyberAmber else CyberGreen,
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.toggleOfflineMode() }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Specific tab rendering
                AnimatedContent(
                    targetState = currentTab,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(250)) togetherWith fadeOut(animationSpec = tween(200))
                    },
                    label = "TabTransition"
                ) { targetTab ->
                    when (targetTab) {
                        "DASHBOARD" -> DashboardScreen(
                            viewModel = viewModel,
                            projects = projectsList,
                            donations = donationsList,
                            volunteers = volunteersList,
                            language = language,
                            activeRole = activeRole
                        )
                        "FIELD OPS" -> FieldOpsScreen(
                            viewModel = viewModel,
                            projects = projectsList,
                            language = language,
                            activeRole = activeRole
                        )
                        "JOIN NGO" -> JoinNgoScreen(
                            viewModel = viewModel,
                            volunteers = volunteersList,
                            language = language,
                            activeRole = activeRole
                        )
                        "REPORTS" -> DonationsAndReportsScreen(
                            viewModel = viewModel,
                            donations = donationsList,
                            language = language,
                            activeRole = activeRole
                        )
                        "SECURITY" -> SecurityScreen(
                            viewModel = viewModel,
                            auditLogs = auditLogsList,
                            language = language,
                            activeRole = activeRole
                        )
                    }
                }
                Spacer(modifier = Modifier.height(60.dp)) // Padding to offset custom navigation bars
            }
        }
    }
}

@Composable
fun AppSubtitleBanner(language: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(CardSlate)
            .border(1.dp, LightSlateBorder, RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Deployment Info",
            tint = CyberBlue,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = Translator.translate(language, "app_subtitle") + " | NextSarathi Core Infrastructure Engine 3.5",
            color = LightMutedText,
            fontSize = 12.sp,
            fontFamily = FontFamily.Monospace,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}

// Top App Bar element with role actions and global controls
@Composable
fun TopAppBarEnsemble(
    viewModel: NgoViewModel,
    isOffline: Boolean,
    syncText: String,
    language: String,
    activeRole: String
) {
    var showRoleMenu by remember { mutableStateOf(false) }
    var showLangMenu by remember { mutableStateOf(false) }

    val borderStrokeCol = LightSlateBorder
    Column(
        modifier = Modifier
            .background(CardSlate)
            .statusBarsPadding()
            .drawBehind {
                drawLine(
                    color = borderStrokeCol,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // App Identity Logo
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Brush.radialGradient(listOf(CyberBlue, SlateBg)))
                        .border(1.5.dp, CyberBlueGlow, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.OfflineBolt,
                        contentDescription = "NextSarathi Logo",
                        tint = DarkText,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "NextSarathi",
                        color = DarkText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        letterSpacing = 1.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(RoundedCornerShape(100))
                                .background(if (isOffline) CyberAmber else CyberGreen)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = syncText,
                            color = if (isOffline) CyberAmber else CyberGreenGlow,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }

            // Quick Configuration Handles
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                
                // Role elevation trigger
                Box {
                    Button(
                        onClick = { showRoleMenu = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SlateBg,
                            contentColor = CyberBlueGlow
                        ),
                        border = BorderStroke(1.dp, LightSlateBorder),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Icon(Icons.Default.ManageAccounts, contentDescription = "Role Trigger", modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(activeRole.substringBefore(" "), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    DropdownMenu(
                        expanded = showRoleMenu,
                        onDismissRequest = { showRoleMenu = false },
                        modifier = Modifier.background(CardSlate).border(1.dp, LightSlateBorder)
                    ) {
                        val roles = listOf("Director (MD)", "Staff", "Volunteer", "Donor")
                        roles.forEach { role ->
                            DropdownMenuItem(
                                text = { Text(role, color = DarkText, fontSize = 13.sp) },
                                onClick = {
                                    viewModel.setRole(role)
                                    showRoleMenu = false
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = when (role) {
                                            "Director (MD)" -> Icons.Default.Shield
                                            "Staff" -> Icons.Default.Work
                                            "Volunteer" -> Icons.Default.Group
                                            else -> Icons.Default.Payment
                                        },
                                        contentDescription = role,
                                        tint = CyberBlueGlow,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            )
                        }
                    }
                }

                // Localization selector
                Box {
                    Button(
                        onClick = { showLangMenu = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SlateBg,
                            contentColor = DarkText
                        ),
                        border = BorderStroke(1.dp, LightSlateBorder),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(34.dp)
                    ) {
                        Icon(Icons.Default.Language, contentDescription = "Lang", modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(language, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    DropdownMenu(
                        expanded = showLangMenu,
                        onDismissRequest = { showLangMenu = false },
                        modifier = Modifier.background(CardSlate).border(1.dp, LightSlateBorder)
                    ) {
                        val langs = listOf("EN" to "English", "HI" to "हिन्दी", "ES" to "Español", "FR" to "Français")
                        langs.forEach { (code, name) ->
                            DropdownMenuItem(
                                text = { Text(name, color = DarkText, fontSize = 13.sp) },
                                onClick = {
                                    viewModel.setLanguage(code)
                                    showLangMenu = false
                                },
                                leadingIcon = {
                                    Text(code, color = CyberAmber, fontWeight = FontWeight.Black, fontSize = 11.sp)
                                }
                            )
                        }
                    }
                }

            }
        }
    }
}

// Customized bottom navigation matching style
@Composable
fun BottomNavigationBarEnsemble(
    currentTab: String,
    onTabSelected: (String) -> Unit,
    language: String
) {
    val bBorderCol = LightSlateBorder
    NavigationBar(
        containerColor = CardSlate,
        tonalElevation = 8.dp,
        modifier = Modifier.drawBehind {
            drawLine(
                color = bBorderCol,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = 1.dp.toPx()
            )
        }
    ) {
        val navItems = listOf(
            Triple("DASHBOARD", Icons.Default.Dashboard, "dashboard"),
            Triple("FIELD OPS", Icons.Default.Work, "field_ops"),
            Triple("JOIN NGO", Icons.Default.Group, "volunteer"),
            Triple("REPORTS", Icons.Default.Receipt, "donations"),
            Triple("SECURITY", Icons.Default.Lock, "security")
        )

        navItems.forEach { (key, icon, langKey) ->
            val label = Translator.translate(language, langKey)
            NavigationBarItem(
                selected = currentTab == key,
                onClick = { onTabSelected(key) },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        modifier = Modifier.size(22.dp)
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 10.sp,
                        fontWeight = if (currentTab == key) FontWeight.Bold else FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = CyberBlueGlow,
                    selectedTextColor = CyberBlueGlow,
                    indicatorColor = SlateBg,
                    unselectedIconColor = LightMutedText,
                    unselectedTextColor = LightMutedText
                )
            )
        }
    }
}

// reusable informational cards
@Composable
fun InfoBadgeCard(
    icon: ImageVector,
    title: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardSlate),
        border = BorderStroke(1.dp, LightSlateBorder)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = color,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = title,
                    color = LightMutedText,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                color = DarkText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun BentoCard(
    backgroundColor: Color,
    contentColor: Color,
    modifier: Modifier = Modifier,
    cornerRadius: androidx.compose.ui.unit.Dp = 24.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val isDark = isSystemInDarkTheme()
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        shape = RoundedCornerShape(cornerRadius),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(1.dp, if (isDark) contentColor.copy(alpha = 0.15f) else Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            content = content
        )
    }
}

// DASHBOARD MODULE WITH CUSTOM GRAPHICS & SYSTEM STATUS PANELS
@Composable
fun DashboardScreen(
    viewModel: NgoViewModel,
    projects: List<Project>,
    donations: List<Donation>,
    volunteers: List<Volunteer>,
    language: String,
    activeRole: String
) {
    val context = LocalContext.current
    val widgets by viewModel.customizableWidgets.collectAsState()
    val slackLogFeed by viewModel.slackLogs.collectAsState()
    val isOffline by viewModel.isOfflineMode.collectAsState()
    var displayWidgetSettings by remember { mutableStateOf(false) }

    // Dashboard Calculations
    val programBudget = projects.sumOf { it.budget }
    val programSpent = projects.sumOf { it.spent }
    val programEfficiency = if (programBudget > 0) (programSpent / programBudget * 100).toInt() else 0
    val totalDonations = donations.sumOf { it.amount }
    
    // Core KPIs Row
    if (widgets["regional_metrics"] == true) {
        Column {
            Text(
                text = Translator.translate(language, "dashboard") + " - Primary KPIs",
                color = DarkText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            val isDark = isSystemInDarkTheme()
            
            // Bento 1: Analytics Hero Blue Box (Total Program Budget)
            val b1Bg = if (isDark) Color(0xFF1E3A8A) else Color(0xFFD1E4FF)
            val b1Text = if (isDark) Color(0xFFD1E4FF) else Color(0xFF001D36)
            
            BentoCard(
                backgroundColor = b1Bg,
                contentColor = b1Text,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isDark) Color(0x33000000) else Color.White.copy(alpha = 0.5f))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Live Impact Metrics",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = b1Text
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(RoundedCornerShape(100))
                                .background(if (isOffline) CyberAmber else CyberGreen)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(14.dp))
                
                Text(
                    text = formatCurrency(programBudget, language),
                    color = b1Text,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.SansSerif
                )
                
                Text(
                    text = Translator.translate(language, "total_budget") + " • Verified",
                    color = b1Text.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 2.dp)
                )
                
                Spacer(modifier = Modifier.height(14.dp))
                
                // Mini bar chart
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    val bars = listOf(0.4f, 0.65f, 0.5f, 0.85f, 1.0f, 0.7f, 0.9f)
                    bars.forEach { heightFactor ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(heightFactor)
                                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                .background(if (isDark) Color(0xFF38BDF8) else Color(0xFF0061A4))
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Bento Row 1: Sync Status (Grey) and Project Health (Pink)
            val b2Bg = if (isDark) Color(0xFF2D3748) else Color(0xFFE1E2EC)
            val b2Text = if (isDark) Color(0xFFCBD5E1) else Color(0xFF44474E)
            val b3Bg = if (isDark) Color(0xFF501E3C) else Color(0xFFF3E0EA)
            val b3Text = if (isDark) Color(0xFFFBCFE8) else Color(0xFF2B121C)
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Bento 2: Offline Sync (Grey)
                BentoCard(
                    backgroundColor = b2Bg,
                    contentColor = b2Text,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "OFFLINE SYNC",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = b2Text.copy(alpha = 0.7f),
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(RoundedCornerShape(100))
                                .background(if (isOffline) CyberAmber else CyberGreen)
                        )
                        Text(
                            text = if (isOffline) "Field Ready" else "Sync Active",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = b2Text
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Small progress bar represent synching
                    LinearProgressIndicator(
                        progress = { 0.85f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp)),
                        color = if (isDark) Color(0xFF38BDF8) else Color(0xFF0061A4),
                        trackColor = b2Text.copy(alpha = 0.2f)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "942 records local queue",
                        fontSize = 9.sp,
                        color = b2Text.copy(alpha = 0.8f)
                    )
                }
                
                // Bento 3: Project Health / Active Projects (Pink)
                BentoCard(
                    backgroundColor = b3Bg,
                    contentColor = b3Text,
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "PROJECT HEALTH",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = b3Text.copy(alpha = 0.7f),
                            letterSpacing = 1.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "${projects.size} Clusters",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = b3Text
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Velocity: " + if (programEfficiency > 80) "Optimal" else "Standard",
                        fontSize = 10.sp,
                        color = b3Text.copy(alpha = 0.8f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Bento Row 2: Security (Green) and Volunteers (Red)
            val b4Bg = if (isDark) Color(0xFF1B380E) else Color(0xFFDCEDC8)
            val b4Text = if (isDark) Color(0xFFDCEDC8) else Color(0xFF111F07)
            val b5Bg = if (isDark) Color(0xFF6F1D1B) else Color(0xFFFFDAD6)
            val b5Text = if (isDark) Color(0xFFFFDAD6) else Color(0xFF410002)
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Bento 4: Security AES Secure E2EE (Green)
                BentoCard(
                    backgroundColor = b4Bg,
                    contentColor = b4Text,
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(100))
                                .background(Color(0xFF386A1F))
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "AES",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "E2EE SECURE",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = b4Text,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = "MFA: Active",
                            fontSize = 9.sp,
                            color = b4Text.copy(alpha = 0.8f)
                        )
                    }
                }
                
                // Bento 5: Volunteers (Red)
                BentoCard(
                    backgroundColor = b5Bg,
                    contentColor = b5Text,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "GLOBAL OPS",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = b5Text.copy(alpha = 0.7f),
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Profile bubbles matching Bento spec
                        Row(
                            horizontalArrangement = Arrangement.spacedBy((-6).dp)
                        ) {
                            val bubbleColors = listOf(Color.Gray, Color.DarkGray, Color.LightGray)
                            bubbleColors.forEach { bColor ->
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(RoundedCornerShape(100))
                                        .background(bColor)
                                        .border(1.5.dp, b5Bg, RoundedCornerShape(100))
                                )
                            }
                        }
                        
                        Text(
                            text = "+${volunteers.size}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = b5Text
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "New units syncing",
                        fontSize = 9.sp,
                        color = b5Text.copy(alpha = 0.8f)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }

    // Dynamic Customizable Layout Settings Drawer Toggle
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Interactive Widgets Control Center",
            color = LightMutedText,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace
        )
        IconButton(
            onClick = { displayWidgetSettings = !displayWidgetSettings }
        ) {
            Icon(
                imageVector = if (displayWidgetSettings) Icons.Default.SettingsSuggest else Icons.Default.Tune,
                contentDescription = "Widget controller Toggle",
                tint = CyberBlueGlow
            )
        }
    }

    AnimatedVisibility(visible = displayWidgetSettings) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            colors = CardDefaults.cardColors(containerColor = CardSlate),
            border = BorderStroke(1.dp, LightSlateBorder)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text("Customize Dashboard Display Widgets", color = DarkText, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Toggle specific high-velocity panels to optimize load latency or monitor fields:", color = LightMutedText, fontSize = 11.sp)
                Spacer(modifier = Modifier.height(10.dp))
                
                val widgetsList = listOf(
                    "regional_metrics" to "KPI Global Metrics Panel",
                    "project_velocity" to "Regional Project Velocity (Canvas Graphics)",
                    "financial_transparency" to "Jira & Asana Coordination Dashboard",
                    "integration_feed" to "Slack Integrations Real-time Feed"
                )

                widgetsList.forEach { (key, label) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(label, color = DarkText, fontSize = 12.sp)
                        Switch(
                            checked = widgets[key] ?: true,
                            onCheckedChange = { viewModel.toggleWidget(key) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = CyberBlueGlow,
                                checkedTrackColor = SlateBg,
                                uncheckedThumbColor = LightMutedText,
                                uncheckedTrackColor = CardSlate
                            )
                        )
                    }
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // CUSTOM DRAWN CANVAS VELOCITY CHART (High-fidelity custom visualizer)
    if (widgets["project_velocity"] == true) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = CardSlate),
            border = BorderStroke(1.dp, LightSlateBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = Translator.translate(language, "project_velocity_chart"),
                    color = DarkText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Reflecting multi-regional task tracking systems & Asana indices",
                    color = LightMutedText,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                if (projects.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No active projects for canvas diagnostics", color = LightMutedText)
                    }
                } else {
                    val canvasGridColor = LightSlateBorder
                    // Custom Draw of Velocity Bars with animations
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(top = 10.dp, bottom = 10.dp)
                    ) {
                        val canvasWidth = size.width
                        val canvasHeight = size.height
                        
                        // Draw Background Sci-Fi Grid lines
                        val horizontalGridLines = 4
                        for (i in 0..horizontalGridLines) {
                            val y = (canvasHeight / horizontalGridLines) * i
                            drawLine(
                                color = canvasGridColor.copy(alpha = 0.4f),
                                start = Offset(0f, y),
                                end = Offset(canvasWidth, y),
                                strokeWidth = 1.dp.toPx(),
                                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                            )
                        }

                        // Plot projects
                        val paddingBetween = 30.dp.toPx()
                        val barWidth = 24.dp.toPx()
                        val maxBarHeight = canvasHeight - 40.dp.toPx()
                        val count = projects.size
                        val stepX = (canvasWidth - paddingBetween * 2) / (count.coerceAtLeast(1))

                        projects.forEachIndexed { index, proj ->
                            val xPos = paddingBetween + stepX * index + (stepX - barWidth) / 2
                            val velocityPercentage = proj.velocity / 100f
                            val barHeight = maxBarHeight * velocityPercentage
                            val yPos = canvasHeight - barHeight - 20.dp.toPx()

                            // Set Color depending on health
                            val barColorScheme = when (proj.health) {
                                "Healthy" -> CyberGreen
                                "On Track" -> CyberBlueGlow
                                "At Risk" -> CyberAmber
                                else -> AlertCrimson
                            }

                            // Raw Rounded Bar
                            drawRoundRect(
                                brush = Brush.verticalGradient(
                                    colors = listOf(barColorScheme, barColorScheme.copy(alpha = 0.3f))
                                ),
                                topLeft = Offset(xPos, yPos),
                                size = Size(barWidth, barHeight),
                                cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
                            )

                            // Outline for glass elevation
                            drawRoundRect(
                                color = barColorScheme,
                                topLeft = Offset(xPos, yPos),
                                size = Size(barWidth, barHeight),
                                cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx()),
                                style = Stroke(width = 1.dp.toPx())
                            )

                            // Overlay dots indicating overdue breaches
                            if (proj.isOverdue) {
                                drawCircle(
                                    color = AlertCrimson,
                                    radius = 6.dp.toPx(),
                                    center = Offset(xPos + barWidth / 2, yPos)
                                )
                            }
                        }
                    }

                    // Project Grid Labels
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        projects.forEach { proj ->
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = proj.title.substringBefore(" "),
                                    color = DarkText,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "${proj.velocity}%",
                                    color = when (proj.health) {
                                        "Healthy" -> CyberGreenGlow
                                        "On Track" -> CyberBlueGlow
                                        "At Risk" -> CyberAmber
                                        else -> AlertCrimson
                                    },
                                    fontSize = 9.sp,
                                    fontFamily = FontFamily.Monospace,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // JIRA & ASANA PROJECT METRIC DECK
    if (widgets["financial_transparency"] == true) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = CardSlate),
            border = BorderStroke(1.dp, LightSlateBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Jira & Asana Operational Status Boards",
                        color = DarkText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = { Toast.makeText(context, "Resynced metadata boards", Toast.LENGTH_SHORT).show() }) {
                        Icon(Icons.Default.CloudSync, contentDescription = "Manual Resync", tint = CyberBlueGlow)
                    }
                }
                Text(
                    text = "Global synchronization indexes updated from Asana & Slack webhooks:",
                    color = LightMutedText,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                projects.take(3).forEach { proj ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(SlateBg)
                            .border(1.dp, LightSlateBorder, RoundedCornerShape(8.dp))
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(0.6f)) {
                            Text(
                                text = proj.title,
                                color = DarkText,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(6.dp)
                                        .clip(RoundedCornerShape(100))
                                        .background(
                                            when (proj.health) {
                                                "Healthy" -> CyberGreen
                                                "On Track" -> CyberBlueGlow
                                                "At Risk" -> CyberAmber
                                                else -> AlertCrimson
                                            }
                                        )
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Asana Sync Enabled - ${proj.health}",
                                    color = LightMutedText,
                                    fontSize = 10.sp
                                )
                            }
                        }

                        // Jira Token integration
                        Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(0.4f)) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Icon(Icons.Default.Link, contentDescription = "Jira Link", tint = CyberBlueGlow, modifier = Modifier.size(12.dp))
                                Text(
                                    text = proj.jiraLink,
                                    color = CyberBlueGlow,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                            Text(
                                text = proj.slackChannel,
                                color = CyberAmber,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }
        }
    }

    // SLACK INTEGRATIONS STREAM
    if (widgets["integration_feed"] == true) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = CardSlate),
            border = BorderStroke(1.dp, LightSlateBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ChatBubble,
                        contentDescription = "Slack Notifications",
                        tint = CyberAmber,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = Translator.translate(language, "slack_integration"),
                        color = DarkText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Live telemetry of broadcast channel calls:",
                    color = LightMutedText,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Console logging layout
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(SlateBg)
                        .border(1.dp, LightSlateBorder, RoundedCornerShape(8.dp))
                        .verticalScroll(rememberScrollState())
                        .padding(8.dp)
                ) {
                    if (slackLogFeed.isEmpty()) {
                        Text("No logs emitted since boot", color = LightMutedText, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                    } else {
                        slackLogFeed.forEach { log ->
                            Text(
                                text = log,
                                color = if (log.contains("ALERT")) AlertCrimson else if (log.contains("Donation")) CyberGreenGlow else LightMutedText,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardMetricCard(
    title: String,
    value: String,
    subText: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardSlate),
        border = BorderStroke(1.dp, LightSlateBorder)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(title, color = LightMutedText, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, color = DarkText, fontSize = 16.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier.height(2.dp))
            Text(subText, color = color.copy(alpha = 0.8f), fontSize = 9.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

// FIELD ACTIONS SCREEN FOR MD & STAFF MEMBERS
@Composable
fun FieldOpsScreen(
    viewModel: NgoViewModel,
    projects: List<Project>,
    language: String,
    activeRole: String
) {
    val context = LocalContext.current
    var isCreatingProject by remember { mutableStateOf(false) }

    // Form states
    var txtTitle by remember { mutableStateOf("") }
    var txtDesc by remember { mutableStateOf("") }
    var txtRegion by remember { mutableStateOf("") }
    var txtBudget by remember { mutableStateOf("") }
    var priorityChoice by remember { mutableStateOf("High") }
    var txtJira by remember { mutableStateOf("") }
    var txtAsana by remember { mutableStateOf("") }
    var txtSlack by remember { mutableStateOf("") }

    val filterSelected by viewModel.projectStatusFilter.collectAsState()
    val searchVal by viewModel.searchQuery.collectAsState()

    // Permission Verification
    val hasWriteAccess = activeRole == "Director (MD)" || activeRole == "Staff"

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = Translator.translate(language, "field_ops"),
                color = DarkText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            if (hasWriteAccess) {
                Button(
                    onClick = { isCreatingProject = !isCreatingProject },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CyberBlue,
                        contentColor = SlateBg
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = if (isCreatingProject) Icons.Default.Close else Icons.Default.Add,
                        contentDescription = "Create Project Icon",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isCreatingProject) "Close Form" else Translator.translate(language, "add_project"),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        if (!hasWriteAccess) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                colors = CardDefaults.cardColors(containerColor = CardSlate),
                border = BorderStroke(1.dp, AlertCrimson.copy(alpha = 0.6f))
            ) {
                Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Security, contentDescription = "Locked", tint = AlertCrimson, modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        "READ ONLY COMPLIANCE VIEW ACTIVE.\nTo launch new field initiatives or edit task timelines, elevate your session role to 'Director (MD)' or 'Staff'.",
                        fontSize = 11.sp,
                        color = LightMutedText,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        // Expanded Project Creation form (M3 standards)
        AnimatedVisibility(visible = isCreatingProject && hasWriteAccess) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                colors = CardDefaults.cardColors(containerColor = CardSlate),
                border = BorderStroke(1.dp, CyberBlueGlow)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Register Integrated Project Program", color = DarkText, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    
                    OutlinedTextField(
                        value = txtTitle,
                        onValueChange = { txtTitle = it },
                        label = { Text("Project Program Title") },
                        colors = transparentTextFieldColors(),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = txtDesc,
                        onValueChange = { txtDesc = it },
                        label = { Text("Operational Description & Direct Mandate") },
                        colors = transparentTextFieldColors(),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = txtRegion,
                        onValueChange = { txtRegion = it },
                        label = { Text("Regional Office / Deployment Sector") },
                        colors = transparentTextFieldColors(),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = txtBudget,
                        onValueChange = { txtBudget = it },
                        label = { Text("Mandated Program Budget (USD)") },
                        colors = transparentTextFieldColors(),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Priority Choices Row
                    Column {
                        Text("Initiative Priority Rating", color = LightMutedText, fontSize = 11.sp)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            listOf("Low", "Medium", "High", "Critical").forEach { rating ->
                                FilterChip(
                                    selected = priorityChoice == rating,
                                    onClick = { priorityChoice = rating },
                                    label = { Text(rating, fontSize = 11.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = CyberBlue,
                                        selectedLabelColor = SlateBg,
                                        containerColor = SlateBg,
                                        labelColor = LightMutedText
                                    )
                                )
                            }
                        }
                    }

                    // Multi-system Webhook bindings
                    Text("Connected System Synchronization Webhooks", color = CyberBlueGlow, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = txtJira,
                            onValueChange = { txtJira = it },
                            label = { Text("Jira Project Issue ID") },
                            placeholder = { Text("SARATHI-102") },
                            colors = transparentTextFieldColors(),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = txtAsana,
                            onValueChange = { txtAsana = it },
                            label = { Text("Asana Workspace Task") },
                            placeholder = { Text("projects/asana-id") },
                            colors = transparentTextFieldColors(),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.weight(1f)
                        )
                    }

                    OutlinedTextField(
                        value = txtSlack,
                        onValueChange = { txtSlack = it },
                        label = { Text("Slack Telemetry Target Channel") },
                        placeholder = { Text("#water-ops") },
                        colors = transparentTextFieldColors(),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = {
                            val parsedBudget = txtBudget.toDoubleOrNull() ?: 0.0
                            if (txtTitle.isBlank() || txtRegion.isBlank() || parsedBudget <= 0.0) {
                                Toast.makeText(context, "Fill in Title, Region, and positive Budget.", Toast.LENGTH_SHORT).show()
                            } else {
                                viewModel.createProject(
                                    title = txtTitle,
                                    description = txtDesc,
                                    region = txtRegion,
                                    budget = parsedBudget,
                                    priority = priorityChoice,
                                    jiraLink = txtJira,
                                    asanaLink = txtAsana,
                                    slackChannel = txtSlack
                                )
                                // Clear
                                txtTitle = ""
                                txtDesc = ""
                                txtRegion = ""
                                txtBudget = ""
                                txtJira = ""
                                txtAsana = ""
                                txtSlack = ""
                                isCreatingProject = false
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = CyberGreen, contentColor = SlateBg),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    ) {
                        Text("E2EE Register & Broadcast Project Cluster", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Search engine inputs
        OutlinedTextField(
            value = searchVal,
            onValueChange = { viewModel.searchQuery.value = it },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = LightMutedText) },
            placeholder = { Text("Filter projects by keyword or region...", color = LightMutedText) },
            colors = transparentTextFieldColors(),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Filter chips bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("All", "In Progress", "Planning", "Completed", "Overdue").forEach { filter ->
                FilterChip(
                    selected = if (filter == "Overdue") {
                        false // managed dynamically
                    } else filterSelected == filter,
                    onClick = {
                        if (filter == "Overdue") {
                            viewModel.searchQuery.value = "[Overdue]"
                        } else {
                            viewModel.projectStatusFilter.value = filter
                            if (viewModel.searchQuery.value == "[Overdue]") {
                                viewModel.searchQuery.value = ""
                            }
                        }
                    },
                    label = { Text(filter) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = CyberBlueGlow,
                        selectedLabelColor = SlateBg,
                        containerColor = CardSlate,
                        labelColor = LightMutedText
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Project Items List
        val filteredProjects = projects.filter {
            val matchesSearch = it.title.contains(searchVal, ignoreCase = true) || 
                               it.region.contains(searchVal, ignoreCase = true) ||
                               (searchVal == "[Overdue]" && it.isOverdue)
            
            val matchesStatus = filterSelected == "All" || it.status == filterSelected
            matchesSearch && matchesStatus
        }

        if (filteredProjects.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Dangerous, contentDescription = "None", tint = LightMutedText, modifier = Modifier.size(48.dp))
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("No matched NextSarathi projects in cache", color = LightMutedText)
                }
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                filteredProjects.forEach { proj ->
                    ProjectItemCardEnsemble(
                        project = proj,
                        hasWriteAccess = hasWriteAccess,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

// Single high-fidelity project item design
@Composable
fun ProjectItemCardEnsemble(
    project: Project,
    hasWriteAccess: Boolean,
    viewModel: NgoViewModel
) {
    val context = LocalContext.current
    var isExpandedDetails by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable { isExpandedDetails = !isExpandedDetails },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CardSlate),
        border = BorderStroke(
            1.dp,
            if (project.isOverdue) AlertCrimson else LightSlateBorder
        )
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            // Priority Tag Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Priority badges
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(
                            when (project.priority) {
                                "Critical" -> AlertCrimson.copy(alpha = 0.2f)
                                "High" -> CyberAmber.copy(alpha = 0.2f)
                                else -> CyberBlue.copy(alpha = 0.2f)
                            }
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Priority: ${project.priority}",
                        color = when (project.priority) {
                            "Critical" -> AlertCrimson
                            "High" -> CyberAmber
                            else -> CyberBlueGlow
                        },
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Overdue warning switch
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (project.isOverdue) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Overdue Alert",
                            tint = AlertCrimson,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("DEADLINE BREACH", color = AlertCrimson, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }

                    if (hasWriteAccess) {
                        IconButton(
                            onClick = { viewModel.toggleProjectOverdue(project) },
                            modifier = Modifier.size(24.dp)
                        ) {
                            Icon(
                                imageVector = if (project.isOverdue) Icons.Default.CancelPresentation else Icons.Default.ReportGmailerrorred,
                                contentDescription = "Toggle Overdue Flag",
                                tint = if (project.isOverdue) CyberBlueGlow else AlertCrimson,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Project Title text
            Text(
                text = project.title,
                color = DarkText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Region Sector: ${project.region}",
                color = LightMutedText,
                fontSize = 11.sp,
                fontFamily = FontFamily.Monospace
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Progress Slider Visuals (M3 standard)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Operational Velocity Metric:",
                    color = LightMutedText,
                    fontSize = 11.sp
                )
                Text(
                    text = "${project.velocity}% [${project.health}]",
                    color = when (project.health) {
                        "Healthy" -> CyberGreenGlow
                        "On Track" -> CyberBlueGlow
                        "At Risk" -> CyberAmber
                        else -> AlertCrimson
                    },
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            LinearProgressIndicator(
                progress = { project.velocity / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(100)),
                color = when (project.health) {
                    "Healthy" -> CyberGreen
                    "On Track" -> CyberBlueGlow
                    "At Risk" -> CyberAmber
                    else -> AlertCrimson
                },
                trackColor = SlateBg
            )

            // Conditional view of project integration details
            AnimatedVisibility(visible = isExpandedDetails) {
                Column(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(SlateBg)
                        .border(1.dp, LightSlateBorder, RoundedCornerShape(8.dp))
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Project Description Matrix:",
                        color = CyberBlueGlow,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = project.description,
                        color = DarkText,
                        fontSize = 11.sp
                    )

                    Divider(color = LightSlateBorder)

                    // Budgets Detail
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Total Outflow Budget", color = LightMutedText, fontSize = 10.sp)
                            Text(formatCurrency(project.budget, "EN"), color = DarkText, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Asana Velocity Card Status", color = LightMutedText, fontSize = 10.sp)
                            Text(project.status, color = CyberAmber, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    // Direct Webhook Links
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Jira Integrated Card ID", color = LightMutedText, fontSize = 10.sp)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.BugReport, contentDescription = "Bug ID", tint = CyberBlueGlow, modifier = Modifier.size(12.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(project.jiraLink, color = CyberBlueGlow, fontSize = 11.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
                            }
                        }

                        Column(horizontalAlignment = Alignment.End) {
                            Text("Slack Telemetry Target", color = LightMutedText, fontSize = 10.sp)
                            Text(project.slackChannel, color = CyberAmber, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                        }
                    }

                    // Dynamic Velocity Editor (Available for MD & Staff)
                    if (hasWriteAccess) {
                        Divider(color = LightSlateBorder)
                        Text("Real-time Metrics Dispatch Controls", color = DarkText, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            listOf(
                                Triple(30, "At Risk", "Slow"),
                                Triple(65, "On Track", "Normal"),
                                Triple(95, "Healthy", "Sprint")
                            ).forEach { (vel, hlt, label) ->
                                Button(
                                    onClick = { viewModel.updateProjectVelocity(project, vel, hlt) },
                                    colors = ButtonDefaults.buttonColors(containerColor = CardSlate, contentColor = DarkText),
                                    border = BorderStroke(1.dp, LightSlateBorder),
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.weight(1f),
                                    contentPadding = PaddingValues(4.dp)
                                ) {
                                    Text("$label ($vel%)", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }

                        // Archive button
                        Button(
                            onClick = { viewModel.deleteProject(project) },
                            colors = ButtonDefaults.buttonColors(containerColor = AlertCrimson.copy(alpha = 0.2f), contentColor = AlertCrimson),
                            border = BorderStroke(1.dp, AlertCrimson),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            Icon(Icons.Default.DeleteForever, contentDescription = "Archive Project", modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Archive Project Record", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

// VOLUNTEER REGISTRATION HUB PORTAL
@Composable
fun JoinNgoScreen(
    viewModel: NgoViewModel,
    volunteers: List<Volunteer>,
    language: String,
    activeRole: String
) {
    val context = LocalContext.current
    var txtName by remember { mutableStateOf("") }
    var txtEmail by remember { mutableStateOf("") }
    var txtSkills by remember { mutableStateOf("") }
    var selectedRoleApplied by remember { mutableStateOf("Field Operations Liaison") }

    val hasVerifyAccess = activeRole == "Director (MD)" || activeRole == "Staff"

    Column {
        Text(
            text = Translator.translate(language, "volunteer_join_title"),
            color = DarkText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(
            text = "Join global coordination units. All applicants undergo system IAM and background clearance logs.",
            color = LightMutedText,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // JOINING ACTION CARD FORM
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardSlate),
            border = BorderStroke(1.dp, LightSlateBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("NGO Volunteer Application Form", color = DarkText, fontSize = 14.sp, fontWeight = FontWeight.Bold)

                OutlinedTextField(
                    value = txtName,
                    onValueChange = { txtName = it },
                    label = { Text("Display / Certificate Full Name") },
                    colors = transparentTextFieldColors(),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = txtEmail,
                    onValueChange = { txtEmail = it },
                    label = { Text("Security Verification Email") },
                    colors = transparentTextFieldColors(),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = txtSkills,
                    onValueChange = { txtSkills = it },
                    label = { Text("Operational Skills (Medical, Dev, Logistics etc)") },
                    colors = transparentTextFieldColors(),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                // Select Volunteer Sector Row
                Column {
                    Text("Select Deployment Track", color = LightMutedText, fontSize = 11.sp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("Field Operations Liaison", "Crisis Medic Specialist", "Jira System Coordinator", "Donations Auditor").forEach { track ->
                            FilterChip(
                                selected = selectedRoleApplied == track,
                                onClick = { selectedRoleApplied = track },
                                label = { Text(track, fontSize = 11.sp) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = CyberBlueGlow,
                                    selectedLabelColor = SlateBg,
                                    containerColor = SlateBg,
                                    labelColor = LightMutedText
                                )
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        if (txtName.isBlank() || txtEmail.isBlank() || txtSkills.isBlank()) {
                            Toast.makeText(context, "Please configure name, email, and skill assets.", Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.registerVolunteer(
                                name = txtName,
                                email = txtEmail,
                                skills = txtSkills,
                                role = selectedRoleApplied
                            )
                            // Clear
                            txtName = ""
                            txtEmail = ""
                            txtSkills = ""
                            Toast.makeText(context, "Registration submitted. Pending MD verification.", Toast.LENGTH_LONG).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = CyberBlue, contentColor = SlateBg),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(Translator.translate(language, "submit_application"), fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // LIST OF VOLUNTEERS SYSTEM CONTROL (MD / Staff can verify background checks)
        Text("Deployed Sarathi - Volunteers Directory", color = DarkText, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Text("Total Active Units: ${volunteers.size}", color = LightMutedText, fontSize = 11.sp, modifier = Modifier.padding(bottom = 10.dp))

        if (volunteers.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("No active deployments on records", color = LightMutedText)
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                volunteers.forEach { vol ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = CardSlate),
                        border = BorderStroke(
                            1.dp,
                            if (vol.status == "Certified") CyberGreen.copy(alpha = 0.6f) else LightSlateBorder
                        )
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(vol.name, color = DarkText, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                                    Text(vol.email, color = LightMutedText, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                                }
                                
                                // Status Badges
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(
                                            when (vol.status) {
                                                "Applied" -> CyberAmber.copy(alpha = 0.2f)
                                                "Active" -> CyberBlue.copy(alpha = 0.2f)
                                                "Certified" -> CyberGreen.copy(alpha = 0.2f)
                                                else -> LightSlateBorder.copy(alpha = 0.5f)
                                            }
                                        )
                                        .padding(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = vol.status,
                                        color = when (vol.status) {
                                            "Applied" -> CyberAmber
                                            "Active" -> CyberBlueGlow
                                            "Certified" -> CyberGreenGlow
                                            else -> DarkText
                                        },
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))
                            Text("Skills Profile: ${vol.skills}", color = DarkText, fontSize = 11.sp)
                            Text("Registered Track: ${vol.roleApplied}", color = LightMutedText, fontSize = 11.sp)
                            
                            Spacer(modifier = Modifier.height(6.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Hours Contributed: ${vol.hoursContributed} hrs",
                                    color = CyberBlueGlow,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                // MD Promotable tool
                                if (hasVerifyAccess) {
                                    Button(
                                        onClick = { viewModel.advanceVolunteerStatus(vol) },
                                        colors = ButtonDefaults.buttonColors(containerColor = SlateBg, contentColor = CyberBlueGlow),
                                        border = BorderStroke(1.dp, LightSlateBorder),
                                        shape = RoundedCornerShape(6.dp),
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                                        modifier = Modifier.height(28.dp)
                                    ) {
                                        Icon(Icons.Default.Verified, contentDescription = "Verify", modifier = Modifier.size(12.dp))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text("Promote Status", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// DONATIONS TRACKING & TRANSPARENT REPORTING SCREEN WITH PDF/CSV SIMULATORS
@Composable
fun DonationsAndReportsScreen(
    viewModel: NgoViewModel,
    donations: List<Donation>,
    language: String,
    activeRole: String
) {
    val context = LocalContext.current
    var donorNameInput by remember { mutableStateOf("") }
    var emailInput by remember { mutableStateOf("") }
    var amountInput by remember { mutableStateOf("") }
    var selectedCurrency by remember { mutableStateOf("USD") }

    var selectedReportType by remember { mutableStateOf("Impact Assessment Dossier") }
    var fileTypeOption by remember { mutableStateOf("PDF") }
    var generatedHashResult by remember { mutableStateOf("") }

    Column {
        Text(
            text = Translator.translate(language, "track_donations_title"),
            color = DarkText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(
            text = "Verified financial reporting block. NextSarathi employs secure record checks to map funds to ground water and medical kits.",
            color = LightMutedText,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // SECURE TX PORTAL CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardSlate),
            border = BorderStroke(1.dp, LightSlateBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Simulate Direct Donation Gateway Entry", color = DarkText, fontSize = 14.sp, fontWeight = FontWeight.Bold)

                OutlinedTextField(
                    value = donorNameInput,
                    onValueChange = { donorNameInput = it },
                    label = { Text("Donor Legal Entity Name") },
                    colors = transparentTextFieldColors(),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = emailInput,
                    onValueChange = { emailInput = it },
                    label = { Text("Audit receipt Email ID") },
                    colors = transparentTextFieldColors(),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = amountInput,
                        onValueChange = { amountInput = it },
                        label = { Text("Quantum Amount") },
                        colors = transparentTextFieldColors(),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(0.6f)
                    )

                    // Currencies Choice Box
                    Column(modifier = Modifier.weight(0.4f)) {
                        Text("Currency", color = LightMutedText, fontSize = 10.sp)
                        Row {
                            listOf("USD", "INR", "EUR").forEach { cur ->
                                FilterChip(
                                    selected = selectedCurrency == cur,
                                    onClick = { selectedCurrency = cur },
                                    label = { Text(cur, fontSize = 10.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = CyberAmber,
                                        selectedLabelColor = SlateBg,
                                        containerColor = SlateBg,
                                        labelColor = LightMutedText
                                    )
                                )
                            }
                        }
                    }
                }

                Button(
                    onClick = {
                        val amt = amountInput.toDoubleOrNull() ?: 0.0
                        if (donorNameInput.isBlank() || emailInput.isBlank() || amt <= 0.0) {
                            Toast.makeText(context, "Configure donor details and positive currencies.", Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.trackDonation(donorNameInput, emailInput, amt, selectedCurrency)
                            donorNameInput = ""
                            emailInput = ""
                            amountInput = ""
                            Toast.makeText(context, "Secure Donation transacted successfully.", Toast.LENGTH_LONG).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = CyberGreen, contentColor = SlateBg),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(Translator.translate(language, "record_donation"), fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // EXPORTS ENGINE
        Text(Translator.translate(language, "download_reports"), color = DarkText, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            colors = CardDefaults.cardColors(containerColor = CardSlate),
            border = BorderStroke(1.dp, LightSlateBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Select Stakeholder Report Category", color = DarkText, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                
                listOf("Impact Assessment Dossier", "Regional Transacted Audit Ledger", "Donor Accountability Manifest").forEach { rpt ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (selectedReportType == rpt) SlateBg else CardSlate)
                            .clickable { selectedReportType = rpt }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedReportType == rpt,
                            onClick = { selectedReportType = rpt },
                            colors = RadioButtonDefaults.colors(selectedColor = CyberBlueGlow)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(rpt, color = DarkText, fontSize = 12.sp)
                    }
                }

                Divider(color = LightSlateBorder)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        FilterChip(
                            selected = fileTypeOption == "PDF",
                            onClick = { fileTypeOption = "PDF" },
                            label = { Text("PDF Document", fontSize = 11.sp) }
                        )
                        FilterChip(
                            selected = fileTypeOption == "CSV",
                            onClick = { fileTypeOption = "CSV" },
                            label = { Text("CSV Spreadsheet", fontSize = 11.sp) }
                        )
                    }

                    Button(
                        onClick = {
                            generatedHashResult = viewModel.performExport(fileTypeOption, selectedReportType)
                            Toast.makeText(context, "Exported $selectedReportType as $fileTypeOption", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = CyberBlueGlow, contentColor = SlateBg),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(Icons.Default.Download, contentDescription = "Dl", modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Export", fontSize = 12.sp)
                    }
                }

                if (generatedHashResult.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(6.dp))
                            .background(SlateBg)
                            .padding(8.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.VerifiedUser, contentDescription = "E2EE Check", tint = CyberGreen, modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("COMPLIANCE HASH SECURED", color = CyberGreenGlow, fontSize = 10.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
                            }
                            Text(generatedHashResult, color = LightMutedText, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // TRANSACTIONS LEDGER ITEMS
        Text("Verified Financial Blockchain Ledger", color = DarkText, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Text("Aperiodic real-time validations in progress:", color = LightMutedText, fontSize = 11.sp)

        Spacer(modifier = Modifier.height(10.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            donations.forEach { don ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(CardSlate)
                        .border(1.dp, LightSlateBorder, RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(don.donorName, color = DarkText, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Text(don.email, color = LightMutedText, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                        Text(
                            text = "Txn Hash: ${don.txnHash.take(15)}...",
                            color = CyberBlueGlow,
                            fontSize = 10.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "+ ${don.amount} ${don.currency}",
                            color = CyberGreenGlow,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (don.isSynced) CyberGreen.copy(alpha = 0.2f) else CyberAmber.copy(alpha = 0.2f))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = if (don.isSynced) "VERIFIED LEDGER" else "LOCAL OFFLINE QUEUE",
                                color = if (don.isSynced) CyberGreenGlow else CyberAmber,
                                fontSize = 8.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

// COGNITIVE SECURITY & COMPLIANCE ACCESS SCREEN
@Composable
fun SecurityScreen(
    viewModel: NgoViewModel,
    auditLogs: List<AuditLog>,
    language: String,
    activeRole: String
) {
    val context = LocalContext.current
    val mfaActive by viewModel.mfaEnabled.collectAsState()
    val mfaSecret by viewModel.mfaSecretCode.collectAsState()
    val backupCodesKeys by viewModel.backupCodes.collectAsState()
    val userAccounts by viewModel.userAccounts.collectAsState()

    var showIntegrityVerificationResult by remember { mutableStateOf(false) }
    var integrityLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    // Form inputs
    var showForm by remember { mutableStateOf(false) }
    var newMemberName by remember { mutableStateOf("") }
    var newMemberEmail by remember { mutableStateOf("") }
    var newMemberRole by remember { mutableStateOf("Staff") }
    var showRoleDrop by remember { mutableStateOf(false) }
    var selectedDocsTab by remember { mutableStateOf("firebase") }

    Column {
        Text(
            text = Translator.translate(language, "security"),
            color = DarkText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 6.dp)
        )
        Text(
            text = "Admin Identity governance, user accounts provisioning, secure logs, and GDPR/ISO auditing suites.",
            color = LightMutedText,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        // CONFIG CONTROL IAM SETTINGS Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardSlate),
            border = BorderStroke(1.dp, LightSlateBorder)
        ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Enterprise Authentication protocols - MFA Configuration", color = DarkText, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(0.7f)) {
                        Text("Multi-Factor Authentication (OAuth2 / SAML layer)", color = DarkText, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        Text("Require secure verification codes before modifying financial budgets.", color = LightMutedText, fontSize = 10.sp)
                    }

                    Switch(
                        checked = mfaActive,
                        onCheckedChange = { viewModel.toggleMfa() },
                        colors = SwitchDefaults.colors(checkedThumbColor = CyberGreenGlow, checkedTrackColor = SlateBg)
                    )
                }

                AnimatedVisibility(visible = mfaActive) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(SlateBg)
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("MFA Secret setup key:", color = CyberBlueGlow, fontSize = 11.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
                        Text(mfaSecret, color = DarkText, fontSize = 14.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Black)
                        
                        Divider(color = LightSlateBorder)
                        
                        Text("Backup Emergency Decryption codes:", color = CyberAmber, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            backupCodesKeys.take(2).forEach { code ->
                                Text(code, color = LightMutedText, fontSize = 11.sp, fontFamily = FontFamily.Monospace, modifier = Modifier
                                    .background(CardSlate)
                                    .padding(4.dp))
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // IDENTITY & ACCESS MANAGEMENT (IAM) MANAGER
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardSlate),
            border = BorderStroke(1.dp, LightSlateBorder)
        ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(0.6f)) {
                        Text("Database User Accounts Registry", color = DarkText, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Text("Modify accounts, assign roles, toggle clearance active states.", color = LightMutedText, fontSize = 11.sp)
                    }
                    
                    // Button to add new users (only for administrators: MD/Staff)
                    val hasProvisionAccess = activeRole == "Director (MD)" || activeRole == "Staff"
                    if (hasProvisionAccess) {
                        Button(
                            onClick = { showForm = !showForm },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (showForm) AlertCrimson.copy(alpha = 0.2f) else SlateBg,
                                contentColor = if (showForm) AlertCrimson else CyberBlueGlow
                            ),
                            border = BorderStroke(1.dp, if (showForm) AlertCrimson.copy(alpha = 0.5f) else LightSlateBorder),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                            modifier = Modifier.height(32.dp)
                        ) {
                            Icon(
                                imageVector = if (showForm) Icons.Default.Close else Icons.Default.PersonAdd,
                                contentDescription = "Add User",
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(if (showForm) "Close" else "Invite User", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                // Add New User Form
                AnimatedVisibility(visible = showForm) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(SlateBg)
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text("Invite New Member Credentials", color = CyberBlueGlow, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        
                        OutlinedTextField(
                            value = newMemberName,
                            onValueChange = { newMemberName = it },
                            label = { Text("Name", fontSize = 11.sp) },
                            textStyle = LocalTextStyle.current.copy(fontSize = 12.sp),
                            colors = transparentTextFieldColors(),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = newMemberEmail,
                            onValueChange = { newMemberEmail = it },
                            label = { Text("Email", fontSize = 11.sp) },
                            textStyle = LocalTextStyle.current.copy(fontSize = 12.sp),
                            colors = transparentTextFieldColors(),
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Role Selector dropdown
                        Box(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = newMemberRole,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("App Role", fontSize = 11.sp) },
                                textStyle = LocalTextStyle.current.copy(fontSize = 12.sp),
                                colors = transparentTextFieldColors(),
                                modifier = Modifier.fillMaxWidth(),
                                trailingIcon = {
                                    IconButton(onClick = { showRoleDrop = true }) {
                                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Pick Role", tint = DarkText)
                                    }
                                }
                            )

                            DropdownMenu(
                                expanded = showRoleDrop,
                                onDismissRequest = { showRoleDrop = false },
                                modifier = Modifier.background(CardSlate).border(1.dp, LightSlateBorder)
                            ) {
                                listOf("Director (MD)", "Staff", "Accountant", "Volunteer").forEach { role ->
                                    DropdownMenuItem(
                                        text = { Text(role, color = DarkText, fontSize = 12.sp) },
                                        onClick = {
                                            newMemberRole = role
                                            showRoleDrop = false
                                        }
                                    )
                                }
                            }
                        }

                        Button(
                            onClick = {
                                if (newMemberName.isNotBlank() && newMemberEmail.isNotBlank()) {
                                    viewModel.createUserAccount(newMemberName, newMemberEmail, newMemberRole)
                                    Toast.makeText(context, "User registered in NextSarathi Database!", Toast.LENGTH_SHORT).show()
                                    newMemberName = ""
                                    newMemberEmail = ""
                                    newMemberRole = "Staff"
                                    showForm = false
                                } else {
                                    Toast.makeText(context, "Please configure both name and email.", Toast.LENGTH_SHORT).show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = CyberGreen, contentColor = Color.White),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Provision & Broadcast Credentials", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }

                Divider(color = LightSlateBorder)

                // List of registered database users
                if (userAccounts.isEmpty()) {
                    Text("No user accounts found in regional cluster.", color = LightMutedText, fontSize = 11.sp, textAlign = TextAlign.Center)
                } else {
                    userAccounts.forEach { u ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(8.dp))
                                .background(SlateBg.copy(alpha = 0.5f))
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(0.55f)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = when(u.role) {
                                            "Director (MD)" -> Icons.Default.Shield
                                            "Staff" -> Icons.Default.Work
                                            "Accountant" -> Icons.Default.AccountBalance
                                            else -> Icons.Default.Group
                                        },
                                        contentDescription = u.role,
                                        tint = if (u.status == "Active") CyberBlueGlow else LightMutedText,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = u.name,
                                        color = if (u.status == "Active") DarkText else LightMutedText,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Text(u.email, color = LightMutedText, fontSize = 10.sp)
                            }

                            Row(
                                modifier = Modifier.weight(0.45f),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Role Badge
                                Text(
                                    text = u.role.substringBefore(" "),
                                    color = if (u.status == "Active") CyberAmber else LightMutedText,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    modifier = Modifier
                                        .background(CardSlate)
                                        .border(0.5.dp, LightSlateBorder)
                                        .padding(horizontal = 4.dp, vertical = 2.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))

                                // Status Badge
                                Text(
                                    text = u.status,
                                    color = if (u.status == "Active") CyberGreenGlow else AlertCrimson,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .background(if (u.status == "Active") CyberGreen.copy(alpha = 0.1f) else AlertCrimson.copy(alpha = 0.1f))
                                        .padding(horizontal = 4.dp, vertical = 2.dp)
                                )

                                // Admin actions if active role is MD or Staff, and not editing primary admin account
                                val showAdminActions = (activeRole == "Director (MD)" || activeRole == "Staff") && !u.isPrimaryAdmin
                                if (showAdminActions) {
                                    Spacer(modifier = Modifier.width(4.dp))
                                    // Toggle Status (Suspend / Activate)
                                    IconButton(
                                        onClick = { viewModel.toggleUserAccountStatus(u) },
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Icon(
                                            imageVector = if (u.status == "Active") Icons.Default.Block else Icons.Default.CheckCircleOutline,
                                            contentDescription = "Toggle user",
                                            tint = if (u.status == "Active") CyberAmber else CyberGreenGlow,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }

                                    // Delete user account (Revoke)
                                    IconButton(
                                        onClick = { viewModel.deleteUserAccount(u) },
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Revoke access",
                                            tint = AlertCrimson,
                                            modifier = Modifier.size(13.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // PRODUCTION AUTHENTICATION INTEGRATION GUIDELINE REFERENCE CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardSlate),
            border = BorderStroke(1.dp, LightSlateBorder)
        ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.VerifiedUser, contentDescription = "Production Guide", tint = CyberGreenGlow, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Production Setup: How to Auth & Log in?", color = DarkText, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                
                Text(
                    text = "A guide on integrating physical authentication gates, MFA tokens, and role-based claims in real production after your application is published.",
                    color = LightMutedText,
                    fontSize = 11.sp
                )

                // Sub-Tabs within the documentation
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(SlateBg)
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    listOf("firebase" to "Firebase Auth", "jwt" to "Spring/Node JWT", "metadata" to "Rules").forEach { tab ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (selectedDocsTab == tab.first) CardSlate else Color.Transparent)
                                .clickable { selectedDocsTab = tab.first }
                                .padding(vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = tab.second,
                                color = if (selectedDocsTab == tab.first) CyberBlueGlow else LightMutedText,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(SlateBg)
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    when (selectedDocsTab) {
                        "firebase" -> {
                            Text("1. Hooking Firebase SDK & OAuth2", color = CyberBlueGlow, fontSize = 11.sp, fontWeight = FontWeight.Black)
                            Text(
                                "In production, add 'com.google.firebase:firebase-auth-ktx' to build.gradle and call:\n\n" +
                                "FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)\n\n" +
                                "You can also hook up Gmail/Google Workspace SSO easily using the One-Tap Sign In SDK to permit access only to employees matching your corporate domain (e.g. '@sarathi.org').",
                                color = LightMutedText,
                                fontSize = 10.sp
                            )
                        }
                        "jwt" -> {
                            Text("2. Custom JWT Backend Role Mapping", color = CyberAmber, fontSize = 11.sp, fontWeight = FontWeight.Black)
                            Text(
                                "If using custom Node.js/Spring endpoints, include an Authorization: Bearer <JWT> header. The payload must verify and authorize user claims:\n\n" +
                                "{\n  \"sub\": \"1234567890\",\n  \"role\": \"Staff\",\n  \"permissions\": [\"create:projects\", \"read:logs\"]\n}\n\n" +
                                "Always perform JWT verification server-side using middleware constraints before saving SQLite records.",
                                color = LightMutedText,
                                fontSize = 10.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                        else -> {
                            Text("3. Provisioning Admin/Director Accounts", color = CyberGreenGlow, fontSize = 11.sp, fontWeight = FontWeight.Black)
                            Text(
                                "• Primary Director (MD): Standard accounts are bootstrapped using secure cloud consoles or secure migrations.\n" +
                                "• Inviting Staff & Accountants: The Director logs in, fills out the Invite User fields, and triggers a server hook that sends an automated email invitation bearing a one-time setup code (MFA token) securely.",
                                color = LightMutedText,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // REAL-TIME INTEGRITY RECORD SCANNER
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = CardSlate),
            border = BorderStroke(1.dp, LightSlateBorder)
        ) {
            Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("E2EE Cryptographic Database Integrity Audit", color = DarkText, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Scan all offline cached entries & matching regional logs with active secure keys:", color = LightMutedText, fontSize = 11.sp)

                Button(
                    onClick = {
                        integrityLoading = true
                        showIntegrityVerificationResult = false
                        viewModel.addSystemLog("Database Cryptographic Integrity Audit run", "Checksum matching: SUCCESS.")
                        scope.launch {
                            kotlinx.coroutines.delay(1200)
                            integrityLoading = false
                            showIntegrityVerificationResult = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = SlateBg, contentColor = CyberBlueGlow),
                    border = BorderStroke(1.dp, LightSlateBorder),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (integrityLoading) {
                        CircularProgressIndicator(color = CyberBlueGlow, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text("Verifying local database hashes...")
                    } else {
                        Icon(Icons.Default.GppGood, contentDescription = "Verify", tint = CyberBlueGlow)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Trigger System Safety Check", fontWeight = FontWeight.Bold)
                    }
                }

                if (showIntegrityVerificationResult) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(CyberGreen.copy(alpha = 0.1f))
                            .border(1.dp, CyberGreen, RoundedCornerShape(8.dp))
                            .padding(10.dp)
                    ) {
                        Column {
                            Text("AUDIT STATE: PASSED COMPLIANT", color = CyberGreenGlow, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            Text("Database Records: Validated AES-256 E2EE hashes.", color = DarkText, fontSize = 10.sp)
                            Text("Integrity Footprint: COMPLIANCE-SUCCESS-SHA256-V3.5", color = LightMutedText, fontSize = 10.sp, fontFamily = FontFamily.Monospace)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // COMPLIANCE AUDIT AUDITLOGS LIST
        Text(Translator.translate(language, "compliance_audit"), color = DarkText, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Text("Detailed logs required for NGO transparency acts:", color = LightMutedText, fontSize = 11.sp, modifier = Modifier.padding(bottom = 10.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            auditLogs.forEach { log ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = CardSlate),
                    border = BorderStroke(1.dp, LightSlateBorder)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.HistoryToggleOff, contentDescription = "Log", tint = CyberBlueGlow, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(log.action, color = DarkText, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                            Text(
                                log.userRole,
                                color = CyberAmber,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.Monospace
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(log.details, color = LightMutedText, fontSize = 11.sp)
                        
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Fingerprint: ${log.integrityHash}",
                                color = CyberBlueGlow,
                                fontSize = 9.sp,
                                fontFamily = FontFamily.Monospace
                            )
                            Text(
                                text = "IP: ${log.ipAddress}",
                                color = LightMutedText,
                                fontSize = 9.sp,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }
        }
    }
}

// Custom TextField options
@Composable
fun transparentTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = CyberBlueGlow,
    unfocusedBorderColor = LightSlateBorder,
    focusedLabelColor = CyberBlueGlow,
    unfocusedLabelColor = LightMutedText,
    focusedTextColor = DarkText,
    unfocusedTextColor = DarkText,
    focusedContainerColor = SlateBg,
    unfocusedContainerColor = SlateBg
)

// Currency formatted outputs
fun formatCurrency(amount: Double, language: String): String {
    return try {
        when {
            language == "HI" -> "₹" + NumberFormat.getNumberInstance(Locale("en", "IN")).format(amount)
            language == "ES" -> NumberFormat.getCurrencyInstance(Locale("es", "ES")).format(amount)
            language == "FR" -> NumberFormat.getCurrencyInstance(Locale("fr", "FR")).format(amount)
            else -> NumberFormat.getCurrencyInstance(Locale.US).format(amount)
        }
    } catch (e: Exception) {
        "$amount USD"
    }
}
