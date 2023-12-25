import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void main(String[] args) {
        // 3. データベース接続と結果取得のための変数宣言
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        

        try {
            // 1. ドライバのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. DBと接続する
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root",
                    "Kurofune01Nhk@"
                    );
            
            // 4. DBとやりとりする窓口（Statementオブジェクト）の作成
            String sql = "SELECT * FROM person WHERE id = ?";
            pstmt = con.prepareStatement(sql);
            
            // 5, 6. Select文の実行と結果を格納／代入
            System.out.println("検索キーワードを入力してください > ");
            String input = keyIn();
            
            // PreparedStamementオブジェクトの?に値をセット
            pstmt.setString(1, input);
            rs = pstmt.executeQuery(sql);
            

            // 7. 結果を表示する
            while( rs.next() ) {
                // name列の値を取得
                String name = rs.getString("name");
                // age列の値を取得
                int age = rs.getInt("age");
                // 取得した値を表示
                System.out.println(name);
                System.out.println(age);
            }
        } catch (ClassNotFoundException e) {
            // TODO 自動生成された catch ブロック
            System.err.println("JDBCドライバのロードに失敗しました。");
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        } finally {
            // 8. 接続を閉じる 閉じる処理は後から開いた順で！
            if( rs != null) {
                try {
                    rs.close();
                } catch(SQLException e) {
                    System.err.println("ResultSetを閉じる時にエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if( pstmt != null ) {
                try {
                    pstmt.close();
                } catch(SQLException e) {
                    System.err.println("PreparedStatementを閉じる時にエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if( con != null ) { // tryの外で変数宣言をして、nullで初期化しているのでnullチェック
                try {
                    con.close(); // ConnectionオブジェクトのcloseメソッドでDBとの接続を閉じる
                 // この時、Connection型の参照変数conはtryの外にいないとダメ
                } catch(SQLException e) {
                    System.err.println("データベース切断時にエラーが発生しました。");
                    e.printStackTrace();
                }
            }
        }

    }
    
    /*
     * キーボードから入力された値をStringで返す 引数：なし 戻り値：入力された文字列
     */
    private static String keyIn() {
        String line = null;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            line = key.readLine();
        } catch (IOException e) {
            
        }
        return line;
    }

}
