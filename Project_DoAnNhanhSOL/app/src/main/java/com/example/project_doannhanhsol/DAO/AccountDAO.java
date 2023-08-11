package com.example.project_doannhanhsol.DAO;


import static com.example.project_doannhanhsol.BUS.AccountBUS.AddAccount;
import static com.example.project_doannhanhsol.BUS.HandlingBUS.*;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.project_doannhanhsol.DAO.API.CallAPI;
import com.example.project_doannhanhsol.DTO.Account;
import com.example.project_doannhanhsol.GUI.LocationSettings.LocationSettingsActivity;
import com.example.project_doannhanhsol.GUI.Login.LoginActivity;
import com.example.project_doannhanhsol.GUI.MainActivity;
import com.example.project_doannhanhsol.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDAO {


    public static void LOGINACCOUNT(String Email, String Password, Activity activity, Dialog loadDialog) {
        CallAPI.callAPIAccount.CheckAccount(Email, Password).enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if (response.isSuccessful()) {
                    loadDialog.dismiss();
                    IDACCOUNT = response.body().get(0).getId();
                    EDITOR = SHAREDPREFERENCES.edit();
                    EDITOR.putString(SP_EMAIL, Email);
                    EDITOR.putString(SP_PASSWORD, Password);
                    EDITOR.commit();
                    if (!response.body().get(0).getAddress().isEmpty()) {
                        activity.startActivity(new Intent(activity, MainActivity.class));
                        activity.finish();
                    } else {
                        activity.startActivity(new Intent(activity, LocationSettingsActivity.class));
                        activity.finish();
                    }
                } else {
                    loadDialog.dismiss();
                    Toast.makeText(activity, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                loadDialog.dismiss();
                Log.e(ERROR_CALL_API_ACCOUNT, t.getMessage());
            }
        });
    }

    public static void REGISTRATIONACCOUNT(Account account, Context context, Dialog dialog) {
        CallAPI.callAPIAccount.PostAccount(account).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.body().equals("1")) {
                        dialog.dismiss();
                        context.startActivity(new Intent(context, LoginActivity.class));
                        Toast.makeText(context, TOASTSINGUPSUCCESS, Toast.LENGTH_SHORT).show();
                    }
                    if (response.body().equals("0")) {
                        dialog.dismiss();
                        Toast.makeText(context, TOASTACCOUNTEXISTS, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    dialog.dismiss();
                    Toast.makeText(context, TOASTREGISTRATIONFAILE, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                Log.e(ERROR_CALL_API_ACCOUNT, t.getMessage());
            }
        });
    }

    public static void PutAccount_Location(String Address) {
        CallAPI.callAPIAccount.GetAccount(IDACCOUNT).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    Account account = new Account(response.body().getId(), response.body().getName(), response.body().getEmail(),
                            response.body().getPassword(), response.body().getNumberphone(),
                            Address, response.body().getJoinDate(), response.body().getPicture());
                    CallAPI.callAPIAccount.PutAccount(IDACCOUNT, account).enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e(ERROR_CALL_API_ACCOUNT, t.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e(ERROR_CALL_API_ACCOUNT, t.getMessage());
            }
        });

    }

    public static void PutAccount_edit(String AccountName, String AccountNumberPhone, String AccountAddress, Activity activity, Dialog loadDialog, Uri imageuri, StorageReference reference) {
        CallAPI.callAPIAccount.GetAccount(IDACCOUNT).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {

                    if (imageuri != null) {
                        StorageReference storageReference = reference.child(String.valueOf(IDACCOUNT));
                        storageReference.putFile(imageuri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        loadDialog.dismiss();
                                        PutAccount(AddAccount(response.body().getId(), AccountName, response.body().getEmail(),
                                                response.body().getPassword(), AccountNumberPhone,
                                                AccountAddress, response.body().getJoinDate(), uri.toString()), activity);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        loadDialog.dismiss();
                                    }
                                });
                            }
                        });

                    } else {
                        loadDialog.dismiss();
                        PutAccount(AddAccount(response.body().getId(), AccountName, response.body().getEmail(),
                                response.body().getPassword(), AccountNumberPhone,
                                AccountAddress, response.body().getJoinDate(), response.body().getPicture()), activity);
                    }

                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e(ERROR_CALL_API_ACCOUNT, t.getMessage());
            }
        });

    }

    public static void PutAccount(Account account, Activity activity) {
        CallAPI.callAPIAccount.PutAccount(IDACCOUNT, account).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(activity, "Lưu thành công", Toast.LENGTH_SHORT).show();
                    activity.onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(ERROR_CALL_API_ACCOUNT, t.getMessage());
            }
        });
    }

    public static void GetAccount(Fragment fragment, ImageView ImageView_Picture, TextView TextView_Name, TextView TextView_Email) {
        CallAPI.callAPIAccount.GetAccount(IDACCOUNT).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    Glide.with(fragment).load(response.body().getPicture()).error(R.drawable.ic_user).into(ImageView_Picture);
                    TextView_Name.setText(response.body().getName());
                    TextView_Email.setText(response.body().getEmail());
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e(ERROR_CALL_API_ACCOUNT, t.getMessage());
            }
        });

    }

    public static void GetAccount_edit(Activity activity, ImageView ImageView_Picture, TextView TextView_Name, TextView TextView_Email, TextView TextView_NumberPhone, TextView TextView_Address) {
        CallAPI.callAPIAccount.GetAccount(IDACCOUNT).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    Glide.with(activity).load(response.body().getPicture()).error(R.drawable.ic_user).into(ImageView_Picture);
                    TextView_Name.setText(response.body().getName());
                    TextView_Email.setText(response.body().getEmail());
                    TextView_NumberPhone.setText(response.body().getNumberphone());
                    if (ADDRESS.isEmpty()) {
                        TextView_Address.setText(response.body().getAddress());
                    } else {
                        TextView_Address.setText(ADDRESS);
                    }
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e(ERROR_CALL_API_ACCOUNT, t.getMessage());
            }
        });

    }
    public static void GetAccount_Order(TextView TextView_Name, TextView TextView_NumberPhone, TextView TextView_Address) {
        CallAPI.callAPIAccount.GetAccount(IDACCOUNT).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                if (response.isSuccessful()) {
                    TextView_Name.setText(response.body().getName());
                    if (!response.body().getNumberphone().isEmpty()) {
                        TextView_NumberPhone.setText(response.body().getNumberphone());
                    } else {
                        TextView_NumberPhone.setText("Vui lòng thêm số điện thoại để mua hàng!");
                    }
                    if (!response.body().getAddress().isEmpty()) {
                        TextView_Address.setText(response.body().getAddress());
                    } else {
                        TextView_Address.setText("Vui lòng thêm địa chỉ để mua hàng!");
                    }
                }
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e(ERROR_CALL_API_ACCOUNT, t.getMessage());
            }
        });

    }
}
