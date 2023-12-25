
public class Review05 {

    public static void main(String[] args) {
        // 3. データベース接続と結果取得のための変数宣言

        try {
            // 1. ドライバのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            


            // 2. DBと接続する

            // 4. DBとやりとりする窓口（Statementオブジェクト）の作成

            // 5, 6. Select文の実行と結果を格納／代入

            // 7. 結果を表示する

            // 8. 接続を閉じる
        } catch (ClassNotFoundException e) {
            // TODO 自動生成された catch ブロック
            System.err.println("JDBCドライバのロードに失敗しました。");
            e.printStackTrace();
        }

    }

}
