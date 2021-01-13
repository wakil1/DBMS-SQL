import java.sql.*; //JDBC packages
import java.math.*;
import java.io.*;
import oracle.jdbc.driver.*;
import java.util.*;
public class Phase3 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner= new Scanner(System.in);
		System.out.println("display your username");
		String username=scanner.nextLine();
		System.out.println("display your password");
		String password=scanner.nextLine();
		String dbURL="jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu";
		int x=0;
		try{
			while(x==0) {
			Connection connection=DriverManager.getConnection(dbURL,username,password);
			System.out.println("Select Y to see all the tables,Select G for View specific table, Select U to see the entry tables, select d to delete a row in a table, select U to update a table row, select S to serach for a table and R to see the rental history of a memProfile"
					+ "Otherwise pick a table you want to insert select which table you want to insert A for Store_member B for MemProfile C for Movie D for Watch E for Actor F for Genre Q for Likes H for Movie_Genre \n");
			String str=scanner.nextLine();
			PreparedStatement statement;
			Statement statement1=connection.createStatement();
			String sql,first,Second,Third,forth,Fifth;
			ResultSet resultSet;
			int rows=0;
			char ch=str.charAt(0);
				switch (ch) {
				case 'Y':
				sql = "SELECT table_name FROM tabs";
				statement1=connection.createStatement();
			    resultSet=statement1.executeQuery(sql);
				while(resultSet.next()) {
					System.out.println(resultSet.getString(1));
				}
				connection.close();
				break;
				case 'G':
					System.out.println("which table would you like to view the entries");
					first=scanner.nextLine();
					sql="select * From "+ first;
					resultSet=statement1.executeQuery(sql);
					ResultSetMetaData temp=resultSet.getMetaData();
					int colNumber = temp.getColumnCount();
					while (resultSet.next()) {
					    for (int i = 1; i <= colNumber; i++) {
					        if (i > 1) System.out.print(",  ");
					        String column= resultSet.getString(i);
					        System.out.print(column + " " + temp.getColumnName(i));
					    }
					    System.out.println("");
					}
					
					connection.close();
					break;
				//problem 4
				case 'U':
					System.out.println("Select which table you want to update ");
					first=scanner.nextLine();
					System.out.println("set what you want to update");
					Second=scanner.nextLine();
					System.out.println("Select the conditon ex member_id='232'");
					Third=scanner.nextLine();
					sql="Update "+first+" set "+Second+" where "+Third;
					statement1=connection.createStatement();
					resultSet=statement1.executeQuery(sql);
					System.out.println("Updated");
					connection.close();
					break;
				case 'd':
					System.out.println("Select which table you want to delete ");
					first=scanner.nextLine();
					System.out.println("Select the conditon ex member_id='232'");
					Second=scanner.nextLine();
					sql="Delete from "+first+" where "+Second;
					System.out.println(sql);
					statement1=connection.createStatement();
					resultSet=statement1.executeQuery(sql);
					System.out.println("row deleted");
					connection.close();
					break;
				case 'S': //search movie if Movie name or Actor
					System.out.println("which would you like to search based on Movie name, or Actor? press M for Movie and A for Actor");
					first=scanner.nextLine();
					if(first.equals("M")) {
						System.out.println("What is the movie title?");
						Second=scanner.nextLine();
						Second="'"+Second+"'";
						sql="select M.movie_ID,M.movie_year,M.avg_rating From Movie M where M.title= "+Second;
						statement1=connection.createStatement();
						resultSet=statement1.executeQuery(sql);
						while(resultSet.next()) {
							System.out.println(resultSet.getString(1)+resultSet.getString(2)+resultSet.getString(3));
						}
					}
					if(first.equals("A")) {
						System.out.println("Who is the actors first name?");
						Second=scanner.nextLine();
						Second="'"+Second+"'";
						System.out.println("Who is the actors last name?");
						Third=scanner.nextLine();
						Third="'"+Third+"'";
						sql="select M.movie_ID,M.movie_year,M.avg_rating From Movie M, Actor A where A.movie_ID= M.movie_id and A.first_name="+Second+" and A.last_name="+Third;
						statement1=connection.createStatement();
						resultSet=statement1.executeQuery(sql);
						while(resultSet.next()) {
							System.out.println(resultSet.getString(1)+resultSet.getString(2)+resultSet.getString(3));
						}
						
					}
					else {
						System.out.println("ERROR");
					}
					connection.close();
					break;
				case 'R':
					System.out.println("what is the members_Id ? \n");
					Second=scanner.nextLine();
					Second="'"+Second+"'";
					System.out.println("What is the profile name \n");
					Third=scanner.nextLine();
					Third="'"+Third+"'";
					sql="select M.title From Watch W, Movie M where W.profile_name="+Third+" and W.member_ID="+Second+" and M.movie_id=W.movie_id";
					statement1=connection.createStatement();
					resultSet=statement1.executeQuery(sql);	
					while(resultSet.next()) {
						System.out.println(resultSet.getString(1));
					}
					connection.close();
					break;
				case 'A':
					System.out.println("ENTER the values for store_member you want to insert, press enter after every single insertion");
				first=scanner.nextLine();
				Second=scanner.nextLine();
			   Third=scanner.nextLine();
				forth=scanner.nextLine();
				Fifth=scanner.nextLine();
				int fort=Integer.parseInt(forth);
				int fift=Integer.parseInt(Fifth);
				sql = "INSERT INTO Store_Member(member_ID,first_name,last_name,card_number,exp_date) VALUES(?,?,?,?,?)";
				statement=connection.prepareStatement(sql);
				statement.setString(1, first);
				statement.setString(2, Second);
				statement.setString(3, Third);
				statement.setInt(4, fort);
				statement.setInt(5, fift);
				rows=statement.executeUpdate();
				if (rows>0) {
				System.out.println("created");
				}
				connection.close();
				break;
				case 'B':
					System.out.println("ENTER the values for MemPROFILE you want to insert, press enter after every single insertion");
					first=scanner.nextLine();
					Second=scanner.nextLine();
					sql = "INSERT INTO MemProfile(member_ID,profile_name) VALUES(?,?)";
					statement=connection.prepareStatement(sql);
					statement.setString(1, first);
					statement.setString(2, Second);
					rows=statement.executeUpdate();
					if (rows>0) {
					System.out.println("created");
					}
					connection.close();
					break;
				case 'C':
					System.out.println("ENTER the values for MOVIE you want to insert , press enter after every single insertion");
					first=scanner.nextLine();
					Second=scanner.nextLine();
					Third=scanner.nextLine();
					int thir=Integer.parseInt(Third);
					forth=scanner.nextLine();
					Fifth=scanner.nextLine();
					int Fift=Integer.parseInt(Fifth);
					sql = "INSERT INTO MOVIE(movie_ID,title,movie_year,Producer,avg_rating) VALUES(?,?,?,?,?)";
					statement=connection.prepareStatement(sql);
					statement.setString(1, first);
					statement.setString(2, Second);
					statement.setInt(3, thir);
					statement.setString(4, forth);
					statement.setInt(5, Fift);
					rows=statement.executeUpdate();
					if (rows>0) {
					System.out.println("created");
					}
					connection.close();
					
					break;
				case 'D':
					System.out.println("ENTER the values for watch you want to insert, press enter after every single insertion");
					first=scanner.nextLine();
					int fir=Integer.parseInt(first);
					Second=scanner.nextLine();
				   Third=scanner.nextLine();
					forth=scanner.nextLine();
					sql = "INSERT INTO Watch(rating,movie_ID,profile_name,member_ID) VALUES(?,?,?,?)";
					statement=connection.prepareStatement(sql);
					statement.setInt(1, fir);
					statement.setString(2, Second);
					statement.setString(3, Third);
					statement.setString(4, forth);
					rows=statement.executeUpdate();
					if (rows>0) {
					System.out.println("created");
					}
					connection.close();
					break;
				case 'E':
					System.out.println("ENTER the values for Actor you want to insert, press enter after every single insertion");
					first=scanner.nextLine();
					Second=scanner.nextLine();
					Third=scanner.nextLine();
					forth=scanner.nextLine();
					sql = "INSERT INTO Actor(actor_ID,Movie_ID,first_name,last_name) VALUES(?,?,?,?)";
					statement=connection.prepareStatement(sql);
					statement.setString(1, first);
					statement.setString(2, Second);
					statement.setString(3, Third);
					statement.setString(4, forth);
					rows=statement.executeUpdate();
					if (rows>0) {
					System.out.println("created");
					}
					connection.close();
					break;
				case 'F':
					System.out.println("ENTER the values for genre you want to insert, press enter after every single insertion");
					sql = "INSERT INTO Genre(m_genre) VALUES(?)";
					first=scanner.nextLine();
					statement=connection.prepareStatement(sql);
					statement.setString(1, first);
					rows=statement.executeUpdate();
					if (rows>0) {
					System.out.println("created");
					}
					connection.close();
					break;
				case 'Q':
					System.out.println("ENTER the values for LIKES you want to insert, press enter after every single insertion");
					first=scanner.nextLine();
					Second=scanner.nextLine();
					Third=scanner.nextLine();
					sql = "INSERT INTO Likes(profile_name,member_ID,m_genre) VALUES(?,?,?)";
					statement=connection.prepareStatement(sql);
					statement.setString(1, first);
					statement.setString(2, Second);
					statement.setString(3, Third);
					rows=statement.executeUpdate();
					if (rows>0) {
					System.out.println("created");
					}
					connection.close();
					break;
				case 'H':
					System.out.println("ENTER the values for MOVIE_GENRE you want to insert, press enter after every single insertion");
					first=scanner.nextLine();
					Second=scanner.nextLine();
					sql = "INSERT INTO Movie_genre(m_genre,movie_ID) VALUES(?,?)";
					statement=connection.prepareStatement(sql);
					statement.setString(1, first);
					statement.setString(2, Second);
					rows=statement.executeUpdate();
					if (rows>0) {
					System.out.println("created");
					}
					connection.close();
					break;
				default:
					System.out.println("INCORRECT INPUT TRY AGAIN");
					break;
				}
			
				System.out.println("Press X to exit otherwise press anything else to go back to main menu");
				String p=scanner.nextLine();
				if(p.equals("X")) {
                    System.exit(0);
					System.out.println("what");
					x=1;
							}		
			
		}
		}catch(SQLException e) {
			System.out.println("OOPS");
		}

		}
	}
