# 🏠 LAB 2 — Calcul des Impôts Locaux
analyste: souaid med amine

## 📌 Objectif du Lab

Créer une application Android qui **calcule le montant total des impôts locaux** d'une habitation selon trois critères :

| Critère | Description |
|---|---|
| 📐 Surface | La surface de la maison en m² |
| 🚪 Pièces | Le nombre de pièces |
| 🏊 Piscine | La présence ou non d'une piscine |

---

## 🧮 Formule de Calcul

```
Impôt de base        = Surface × 2.0
Impôt supplémentaire = (Nombre de pièces × 25) + (200 si piscine, sinon 0)
Impôt Total          = Impôt de base + Impôt supplé

https://github.com/user-attachments/assets/a50a3d1b-9b21-4157-8ebe-4d11361d7055


https://github.com/user-attachments/assets/4c60d667-618c-4282-8c68-4b16a796c254


mentaire
```

### Exemple concret (visible dans la démo) :
```
Nom     : Durand
Adresse : 6 rue de la gare
Surface : 120 m²
Pièces  : 4
Piscine : ✅ Oui

→ Impôt de base        : 120 × 2.0       = 240.0
→ Impôt supplémentaire : (4 × 25) + 200  = 300.0
→ Impôt Total                            = 540.0
```

---

## ✨ Fonctionnalités

| Fonctionnalité | Description |
|---|---|
| 📝 Saisie du nom | Champ texte pour le nom du propriétaire |
| 🏠 Saisie de l'adresse | Champ texte pour l'adresse |
| 📐 Saisie de la surface | Champ numérique décimal (ex: 120.5) |
| 🚪 Saisie des pièces | Champ numérique entier |
| 🏊 Checkbox Piscine | Case à cocher (oui / non) |
| 🧮 Bouton Calcul | Lance le calcul et affiche les résultats |
| ✅ Validation | Vérifie que tous les champs sont remplis |
| ⚠️ Gestion d'erreurs | Messages Toast si saisie invalide |
| 👁️ Affichage dynamique | Les résultats apparaissent seulement après calcul |

---

## 🗂️ Structure du Projet

---

## 🎨 Interface Utilisateur — `activity_main.xml`

```
ScrollView                     ← Permet le défilement si le contenu dépasse l'écran
  └── LinearLayout (vertical)
        ├── TextView            ← Titre "Calcul des impôts locaux"
        │
        ├── TextView            ← Label "Nom"
        ├── EditText            ← Champ saisie Nom         (inputType: text)
        │
        ├── TextView            ← Label "Adresse"
        ├── EditText            ← Champ saisie Adresse     (inputType: text)
        │
        ├── TextView            ← Label "Surface (m²)"
        ├── EditText            ← Champ saisie Surface     (inputType: numberDecimal)
        │
        ├── TextView            ← Label "Nombre de pièces"
        ├── EditText            ← Champ saisie Pièces      (inputType: number)
        │
        └── LinearLayout (horizontal)
              ├── CheckBox      ← "Piscine" (coché = true)
              └── Button        ← "Calcul" → lance calculerImpots()
        │
        ├── TextView            ← Résultat : Impôt de base    (visibility: gone → visible)
        ├── TextView            ← Résultat : Impôt supp.      (visibility: gone → visible)
        └── TextView            ← Résultat : Impôt Total      (visibility: gone → visible)
```

> 💡 **Pourquoi ScrollView ?**  
> Sur les petits écrans, tous les champs + résultats peuvent dépasser la hauteur de l'écran.  
> Le `ScrollView` permet à l'utilisateur de faire défiler vers le bas pour tout voir.

---


## 🧠 Nouveaux Concepts Appris — Lab 2

| Concept Java/Android | Explication |
|---|---|
| `EditText` | Champ de saisie utilisateur |
| `getText().toString().trim()` | Lire et nettoyer la saisie |
| `Double.parseDouble()` | Convertir String → double (décimal) |
| `Integer.parseInt()` | Convertir String → int (entier) |
| `try / catch` | Capturer les erreurs de conversion |
| `NumberFormatException` | Erreur si on convertit "abc" en nombre |
| `CheckBox.isChecked()` | Retourne true/false selon l'état |
| Opérateur ternaire `? :` | `condition ? si_vrai : si_faux` |
| `View.VISIBLE` | Rend un élément visible ET occupe l'espace |
| `View.INVISIBLE` | Cache l'élément mais garde l'espace |
| `View.GONE` | Cache l'élément ET libère l'espace |
| `setVisibility()` | Change la visibilité dynamiquement en Java |
| `ScrollView` | Conteneur scrollable (1 seul enfant direct) |
| `static final` | Constante immuable (bonne pratique) |

---


## 🛠️ Comment Lancer le Projet

### Prérequis
- ✅ Android Studio installé (Hedgehog ou plus récent)
- ✅ JDK configuré sur **JetBrains s.r.o.** dans Gradle Settings
- ✅ Émulateur Android **ou** téléphone physique

### Étapes
```
1. Cloner ou télécharger le projet
2. Ouvrir Android Studio → "Open an Existing Project"
3. Sélectionner le dossier LAB2_impôts_locaux
4. Attendre la synchronisation Gradle ⏳
5. Cliquer sur ▶️ Run (ou Shift + F10)
6. Sélectionner l'appareil/émulateur
7. Tester avec les valeurs : Surface=120, Pièces=4, Piscine=✅
   → Résultat attendu : Base=240.0 | Supp=300.0 | Total=540.0 ✅
```

---

## 🐛 Problème Rencontré & Solution

### ❌ Erreur : `jlink executable ... does not exist`

**Cause :** Android Studio utilisait le JDK de l'extension VS Code (`redhat.java`) au lieu de son propre JDK interne (JBR).

**Solution appliquée :**
```
File → Settings
  → Build, Execution, Deployment
    → Build Tools
      → Gradle
        → Gradle JVM criteria
          → Vendor : JetBrains s.r.o.  ✅
          → Version : 21               ✅
→ OK → File → Sync Project with Gradle Files
```

> ⚠️ **Conseil :** Ne pas ouvrir VS Code et Android Studio en même temps si l'extension `redhat.java` est installée dans VS Code.

---



