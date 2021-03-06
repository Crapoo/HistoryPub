/*
    History Pub est une application de jeu de piste proposant de découvrir la ville de Soignies,
    en parcourant cette dernière de bar en bar.

    Copyright (C) 2015
        Matteo Taroli <contact@matteotaroli.be>
        Nathan Raspe <raspe_nathan@live.be>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package be.ipl.mobile.projet.historypub.activites;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import be.ipl.mobile.projet.historypub.R;
import be.ipl.mobile.projet.historypub.config.Config;
import be.ipl.mobile.projet.historypub.pojo.epreuves.EpreuvePhoto;
import be.ipl.mobile.projet.historypub.ucc.GestionEtapes;
/*La gestion de prise de photo est grâcieusement expliquée et disponible sur developer.android.com*/

/**
 * Activité reprenant une épreuve de photographie.
 */
public class PhotoActivity extends EpreuveActivity {
    private static final String TAG = "PhotoActivity";
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    private EpreuvePhoto mEpreuvePhoto;

    private Button mButton;
    private ImageView mPhoto;

    private Uri mUri;
    private boolean mPhotoPrise = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        mEtape = GestionEtapes.getInstance(this).getEtape(getIntent().getIntExtra(Config.EXTRA_ETAPE, 0));
        mEpreuve = mEtape.getEpreuve(getIntent().getStringExtra(Config.EXTRA_EPREUVE));
        mEpreuvePhoto = (EpreuvePhoto) mEpreuve;

        /* Gestion de la prise de photo */
        final Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mUri = getOutputMediaFileUri();
        i.putExtra(MediaStore.EXTRA_OUTPUT, mUri);

        mButton = (Button) findViewById(R.id.picture_btn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mPhotoPrise) {
                    startActivityForResult(i, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                } else {
                    String title = "Bonne réponse! +" + (mEpreuve.getPoints() - mPointsAEnlever) + " points.";
                    augmenterPoints((mEpreuve.getPoints() - mPointsAEnlever));
                    int[] duree = getDuree();
                    Resources res = getResources();

                    String heures = res.getQuantityString(R.plurals.heures, duree[0], duree[0]);
                    String minutes = res.getQuantityString(R.plurals.minutes, duree[1], duree[1]);
                    String secondes = res.getQuantityString(R.plurals.secondes, duree[2], duree[2]);

                    getDialogExplicatif(title, mEtape, mEpreuve, res.getString(R.string.duree_finale, heures, minutes, secondes));
                }
            }
        });

        TextView question = (TextView) findViewById(R.id.question_textView);
        question.setText(mEpreuve.getQuestion());
        mPhoto = (ImageView) findViewById(R.id.photo_imageview);
        initCheatButton();
        initgetHelpButton();
    }

    /**
     * Charge et affiche l'image correspondant à la réponse de l'épreuve.
     */
    private void showReponseImage() {
        try {
            InputStream ims = getAssets().open("images/" + mEpreuvePhoto.getReponse().getReponse());
            Bitmap bitmap = BitmapFactory.decodeStream(ims);
            Log.d(TAG, bitmap.toString());
            mPhoto.setImageBitmap(bitmap);
            mPhoto.setVisibility(View.VISIBLE);
        } catch (IOException e) {
            Log.e(TAG, "Impossible d'afficher la réponse photo - " + e.toString());
        }
    }

    /**
     * Construit le lien URI du fichier dans lequel enregistrer la photo prise.
     *
     * @return URI de l'image
     */
    private static Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    /**
     * Crée et retourne le fichier dans lequel enregistrer la photo prise.
     *
     * @return Fichier image
     */
    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "HistoryPub");
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d("MyCameraApp", "failed to create directory");
            return null;
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    mPhoto.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), mUri));
                    mPhoto.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    Log.d(TAG, "Impossible d'afficher la photo");
                }
                mPhotoPrise = true;
                mCheatButton.setEnabled(false);
                mHelpButton.setEnabled(false);
                mButton.setText(R.string.ok);
            }
        }
    }

    @Override
    public void doHelp() {
        showReponseImage();
                /* Efface l'image après 5secondes */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPhoto.setImageBitmap(null);
            }
        }, 5000);
    }

    @Override
    public void doCheat() {
        showReponseImage();
        mButton.setText(R.string.ok);
        mPhotoPrise = true;
    }
}