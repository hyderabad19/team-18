package com.example.translation;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.api.GoogleAPIException;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateModelManager;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateRemoteModel;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextToSpeech texttospeech;
    EditText ed1;
    Button b1;
    private String sn;
    private String InputString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText) findViewById(R.id.editText);
        b1 = (Button) findViewById(R.id.button);
        texttospeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    texttospeech.setLanguage(Locale.US);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                InputString = ed1.getText().toString();
                // Create an English-German translator:
                final FirebaseTranslatorOptions options =
                        new FirebaseTranslatorOptions.Builder()
                                .setSourceLanguage(FirebaseTranslateLanguage.EN)
                                .setTargetLanguage(FirebaseTranslateLanguage.HI)
                                .build();
                final FirebaseTranslator englishTeluguTranslator =
                        FirebaseNaturalLanguage.getInstance().getTranslator(options);
                FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder()
                        .requireWifi().build();
                final FirebaseTranslateRemoteModel teluguModel =
                        new FirebaseTranslateRemoteModel.Builder(FirebaseTranslateLanguage.HI).build();

                final FirebaseTranslateModelManager modelManager = FirebaseTranslateModelManager.getInstance();

// Get translation models stored on the device.
                modelManager.getAvailableModels(FirebaseApp.getInstance())
                        .addOnSuccessListener(new OnSuccessListener<Set<FirebaseTranslateRemoteModel>>() {
                            @Override
                            public void onSuccess(Set<FirebaseTranslateRemoteModel> models) {
                                boolean isTeluguModelPresent = false;
                                for (FirebaseTranslateRemoteModel model : models) {
                                    Toast.makeText(MainActivity.this, model.getLanguageCode(), Toast.LENGTH_SHORT).show();
                                    if (teluguModel.getLanguageCode().equals(model.getLanguageCode())) {
                                        isTeluguModelPresent = true;

                                    }
                                }

                                if (!isTeluguModelPresent) {
                                    modelManager.downloadRemoteModelIfNeeded(teluguModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            englishTeluguTranslator.translate(InputString).addOnSuccessListener(new OnSuccessListener<String>() {
                                                @Override
                                                public void onSuccess(String s) {
                                                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                                                    String sample="this is code for good";
                                                    texttospeech.speak(sample, TextToSpeech.QUEUE_FLUSH, null);

                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(MainActivity.this, "failed to translate", Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(MainActivity.this, "failed to download " + e.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });

                                }
                                else
                                    englishTeluguTranslator.translate(InputString).addOnSuccessListener(new OnSuccessListener<String>() {
                                        @Override
                                        public void onSuccess(String s) {
                                            Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(MainActivity.this,"MODEL FOUND TRANSLATION FAILED"+e.getMessage(),Toast.LENGTH_SHORT).show();

                                        }
                                    });
                            }


                        });


                //usingdownloadifnotavailiable
                FirebaseModelDownloadConditions conditions1 = new FirebaseModelDownloadConditions.Builder()
                        .requireWifi()
                        .build();
                englishTeluguTranslator.downloadModelIfNeeded(conditions1)
                        .addOnSuccessListener(
                                new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void v) {
                                        // Model downloaded successfully. Okay to start translating.
                                        // (Set a flag, unhide the translation UI, etc.)
                                        englishTeluguTranslator.translate(InputString).addOnSuccessListener(new OnSuccessListener<String>() {
                                            @Override
                                            public void onSuccess(String s) {
                                                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT);
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(MainActivity.this,"translation fail"+e.getMessage(),Toast.LENGTH_SHORT);

                                            }
                                        });
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Model couldn’t be downloaded or other internal error.
                                        Toast.makeText(MainActivity.this,"download fail"+e.getMessage(),Toast.LENGTH_SHORT);

                                        // ...
                                    }
                                });

            }
        });
    }
}
