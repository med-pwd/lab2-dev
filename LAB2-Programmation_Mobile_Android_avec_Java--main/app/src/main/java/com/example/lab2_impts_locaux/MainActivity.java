package com.example.lab2_impts_locaux;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // ══════════════════════════════════════════════
    // ÉTAPE A — Déclarer toutes les variables UI
    // ══════════════════════════════════════════════
    EditText editNom, editAdresse, editSurface, editPieces;
    CheckBox checkPiscine;
    Button btnCalcul;
    TextView textImpotBase, textImpotSupp, textImpotTotal;

    // ══════════════════════════════════════════════
    // CONSTANTES DE CALCUL
    // Définies ici → facile à modifier plus tard
    // ══════════════════════════════════════════════
    static final double TAUX_BASE        = 2.0;   // Par m²
    static final double TAUX_PAR_PIECE   = 25.0;  // Par pièce
    static final double TAXE_PISCINE     = 200.0; // Forfait piscine

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ══════════════════════════════════════════
        // ÉTAPE B — Relier les variables aux éléments XML
        // ══════════════════════════════════════════
        editNom       = findViewById(R.id.editNom);
        editAdresse   = findViewById(R.id.editAdresse);
        editSurface   = findViewById(R.id.editSurface);
        editPieces    = findViewById(R.id.editPieces);
        checkPiscine  = findViewById(R.id.checkPiscine);
        btnCalcul     = findViewById(R.id.btnCalcul);
        textImpotBase = findViewById(R.id.textImpotBase);
        textImpotSupp = findViewById(R.id.textImpotSupp);
        textImpotTotal= findViewById(R.id.textImpotTotal);

        // ══════════════════════════════════════════
        // ÉTAPE C — Écouteur de clic sur le bouton "Calcul"
        // ══════════════════════════════════════════
        btnCalcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculerImpots();
            }
        });
    }

    // ══════════════════════════════════════════════════════
    // ÉTAPE D — La méthode de calcul (séparée pour la clarté)
    // ══════════════════════════════════════════════════════
    private void calculerImpots() {

        // 1. Récupérer les textes saisis par l'utilisateur
        //    .getText()         → objet Editable
        //    .toString()        → convertit en String (texte)
        //    .trim()            → supprime les espaces inutiles
        String nom     = editNom.getText().toString().trim();
        String surface = editSurface.getText().toString().trim();
        String pieces  = editPieces.getText().toString().trim();

        // 2. Vérification : les champs obligatoires sont-ils remplis ?
        //    isEmpty() → retourne true si le texte est vide
        if (nom.isEmpty() || surface.isEmpty() || pieces.isEmpty()) {
            Toast.makeText(this, "⚠️ Veuillez remplir tous les champs !", Toast.LENGTH_SHORT).show();
            return; // On arrête la méthode ici
        }

        // 3. Convertir les textes en nombres
        //    Double.parseDouble() → String → double (nombre décimal)
        //    Integer.parseInt()   → String → int   (nombre entier)
        //    try/catch : si l'utilisateur tape une lettre au lieu d'un chiffre
        double surfaceVal;
        int    piecesVal;

        try {
            surfaceVal = Double.parseDouble(surface);
            piecesVal  = Integer.parseInt(pieces);
        } catch (NumberFormatException e) {
            // NumberFormatException = erreur si on convertit "abc" en nombre
            Toast.makeText(this, "❌ Surface et pièces doivent être des nombres !", Toast.LENGTH_SHORT).show();
            return;
        }

        // 4. Valeurs négatives = impossible
        if (surfaceVal <= 0 || piecesVal <= 0) {
            Toast.makeText(this, "❌ Les valeurs doivent être positives !", Toast.LENGTH_SHORT).show();
            return;
        }

        // 5. Vérifier si la CheckBox Piscine est cochée
        //    isChecked() → retourne true (cochée) ou false (non cochée)
        boolean aPiscine = checkPiscine.isChecked();

        // ══════════════════════════════════════
        // 6. CALCUL DES IMPÔTS
        // ══════════════════════════════════════

        // Impôt de base = surface × 2.0
        double impotBase = surfaceVal * TAUX_BASE;

        // Impôt supplémentaire = (nb pièces × 25) + (200 si piscine, 0 sinon)
        // L'opérateur ternaire :  condition ? valeur_si_vrai : valeur_si_faux
        double impotSupp = (piecesVal * TAUX_PAR_PIECE) + (aPiscine ? TAXE_PISCINE : 0);

        // Impôt total = somme des deux
        double impotTotal = impotBase + impotSupp;

        // ══════════════════════════════════════
        // 7. AFFICHER LES RÉSULTATS
        // ══════════════════════════════════════

        // setText() met à jour le texte des TextViews
        textImpotBase.setText("Impôt de base  : " + impotBase);
        textImpotSupp.setText("Impôt supplémentaire  : " + impotSupp);
        textImpotTotal.setText("Impôt Total  : " + impotTotal);

        // Rendre les TextViews visibles
        // View.VISIBLE → affiché | View.GONE → caché (ne prend pas de place)
        textImpotBase.setVisibility(View.VISIBLE);
        textImpotSupp.setVisibility(View.VISIBLE);
        textImpotTotal.setVisibility(View.VISIBLE);
    }
}
