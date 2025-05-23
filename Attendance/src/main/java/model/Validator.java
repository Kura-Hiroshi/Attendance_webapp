package model;

public class Validator {
	/**
     * フォームに入力された名前のバリデーションチェックを行うメソッド
     *
     * @param name チェック対象の名前（String型）
     * @return true：1文字以上50文字以下、false：それ以外
     */
    public static boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        int length = name.length();
        return length >= 1 && length <= 50;
    }
    
    /**
     * フォームに入力された事業所名のバリデーションチェックを行うメソッド
     *
     * @param companyName チェック対象の事業所名（String型）
     * @return true：1文字以上100文字以下、false：それ以外
     */
    public static boolean isValidCompanyName(String companyName) {
        if (companyName == null) {
            return false;
        }
        int length = companyName.length();
        return length >= 1 && length <= 100;
    }
    
    /**
     * フォームに入力されたパスワードのバリデーションチェックを行うメソッド
     *
     * @param password チェック対象のパスワード（String型）
     * @return true：8文字以上100文字以下の半角英数字、false：それ以外
     */
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        // 長さのチェック
        if (password.length() < 8 || password.length() > 100) {
            return false;
        }

        // 半角英数字かどうかのチェック（正規表現）
        return password.matches("^[a-zA-Z0-9]+$");
    }
    
    /**
     * フォームに入力された事業所IDのバリデーションチェックを行うメソッド
     *
     * @param companyId チェック対象の事業所ID（String型）
     * @return true：8文字以上50文字以下の半角英数字、false：それ以外
     */
    public static boolean isValidCompanyId(String companyId) {
        if (companyId == null) {
            return false;
        }
        // 長さチェック
        if (companyId.length() < 8 || companyId.length() > 50) {
            return false;
        }

        // 半角英数字かどうかチェック
        return companyId.matches("^[a-zA-Z0-9]+$");
    }
    
    /**
     * フォームに入力された従業員名のバリデーションチェックを行うメソッド
     *
     * @param employeeName チェック対象の従業員名（String型）
     * @return true：1文字以上50文字以下、false：それ以外
     */
    public static boolean isValidEmployeeName(String employeeName) {
        if (employeeName == null) {
            return false;
        }
        int length = employeeName.length();
        return length >= 1 && length <= 50;
    }
}
