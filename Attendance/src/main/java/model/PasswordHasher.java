package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

    /**
     * フォームに入力されたパスワードをSHA-256でハッシュ化するメソッド
     *
     * @param password ハッシュ化対象のパスワード（String型）
     * @return ハッシュ化された文字列（16進表現）／エラー時はnull
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException{
        if (password == null) {
            return null;
        }

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            // バイト配列を16進文字列に変換
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b)); // 2桁の16進数に変換
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // SHA-256がサポートされていない場合（通常ありえません）
            e.printStackTrace();
            throw new NoSuchAlgorithmException("SHA-256がサポートされていないため、パスワードのハッシュ化に失敗しました。");
        }
    }
}
