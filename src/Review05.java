import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Review05 {

    public static void main(String[] args) {
        // 3. データベース接続と結果取得のための変数宣言
        Connection con = null;
        Statement stmt = null;
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
            stmt = con.createStatement();
            
            // 5, 6. Select文の実行と結果を格納／代入
            String sql = "SELECT * FROM person WHERE id = 2";
            rs = stmt.executeQuery(sql);
            

            // 7. 結果を表示する
            while( rs.next() ) {
                // Name列の値を取得
                String name = rs.getString("Name");
                // 取得した値を表示
                System.out.println(name);
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
            if( stmt != null ) {
                try {
                    stmt.close();
                } catch(SQLException e) {
                    System.err.println("Statementを閉じる時にエラーが発生しました。");
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

}
