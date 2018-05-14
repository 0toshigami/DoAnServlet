/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.*;
import java.util.*;

/**
 *
 * @author NoName
 */
public class SPDB {
    
    public static ArrayList getInfoTraLoi(String user) {
        try {
            Connection con = DataProvider.Connect();
            if (con != null) {
                ArrayList info = new ArrayList();
                String sql =    "SELECT ch.NoiDung, tl.CauTraLoi, ch.CauTraLoiDung " +
                                "FROM traloi tl, cauhoi ch " +
                                "WHERE tl.SttCauHoi = ch.STT AND tl.Username = '"+user+"'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    Info i = new Info();
                    i._NoiDung = rs.getString("NoiDung");
                    i._CauTL = rs.getString("CauTraLoi");
                    i._CauTLDung = rs.getString("CauTraLoiDung");
                    info.add(i);
                }
                return info;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public static ArrayList getHSTraLoi() {
        try {
            Connection con = DataProvider.Connect();
            if (con != null) {
                ArrayList dstl = new ArrayList();
                String sql = "SELECT DISTINCT Username FROM traloi";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    TraLoi tl = new TraLoi();
                    tl._Username = rs.getString("Username");
                    dstl.add(tl);
                }                
                return dstl;
            } else {
                return null;
            }                                    
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public static int InsertTraLoi(ArrayList arr) {
        try {
            Connection con = DataProvider.Connect();
            if (con != null) {
                String sql = "insert into traloi(Username, SttCauHoi, CauTraLoi) values";
                for (int i = 0; i < arr.size(); i++) {
                    TraLoi tl = (TraLoi)arr.get(i);
                    if (i == arr.size()-1) {
                        sql += "('"+tl._Username+"',"+tl._SttCauHoi+",'"+tl._CauTL+"')";
                    } else {
                        sql += "('"+tl._Username+"',"+tl._SttCauHoi+",'"+tl._CauTL+"'),";
                    }
                    //System.out.println("('"+tl._Username+"',"+tl._SttCauHoi+",'"+tl._CauTL+"')");
                }
                PreparedStatement st = con.prepareStatement(sql);
                //st.setString(1, tenPB);
                int k = st.executeUpdate();
                con.close();
                return k;
            } else {
                return 0;
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
            return -1;
        }
    }
    
    public static ArrayList getQuestion() {
        try {
            Connection con = DataProvider.Connect();
            if (con != null) {
                ArrayList dsch = new ArrayList();
                String sql = "SELECT STT, NoiDung, CauTraLoiDung FROM cauhoi";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    CauHoi ch = new CauHoi();
                    ch._Stt = Integer.parseInt(rs.getString("STT"));
                    ch._NoiDung = rs.getString("NoiDung");
                    ch._CauTLDung = rs.getString("CauTraLoiDung");
                    dsch.add(ch);
                }
                return dsch;
            }
            else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public static ArrayList checkAccount(String name, String pass) {
        try {
            Connection con = DataProvider.Connect();
            if (con != null) {
                ArrayList lst = new ArrayList();
                String sql = "SELECT * FROM taikhoan WHERE Username = '"+name+"' AND Password = '"+pass+"'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs.next() == false) {
                    //out.print("There is no one like that");
                    return null;
                }
                else {
                    //out.print("Found one!");
                    TaiKhoan tk = new TaiKhoan();
                    tk._Username = rs.getString("Username");
                    tk._Password = rs.getString("Password");
                    tk._Type = Integer.parseInt(rs.getString("Type"));
                    lst.add(tk);
                    return lst;
                }
            } else {
                //out.print("Failed to connect to database");
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public static ArrayList TimTatCaTaiKhoan() {
        try {
            ArrayList dstk = new ArrayList();
            Connection con = DataProvider.Connect();
            if (con != null) {
                String sql = "SELECT Username, Password, Type FROM taikhoan";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql); 
                while (rs.next()) {
                    TaiKhoan tk = new TaiKhoan();
                    tk._Username = rs.getString("username");
                    tk._Password = rs.getString("password");
                    tk._Type = Integer.parseInt(rs.getString("type"));
                    dstk.add(tk);
                }                               
            }
            else {
                System.out.println("Return Null");
                con.close();
                return null;
            }
            System.out.println("Return List");
            con.close(); 
            return dstk;            
        }
        catch (Exception ex) {
            System.out.println("Return Null");
            return null;
        }
    }
}
