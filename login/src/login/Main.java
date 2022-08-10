package login;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a;
		String id = null,pw=null,name = null;
		int help;
		
		System.out.println
		("로그인을 하려면 1, 회원가입을 하려면 2, 회원삭제는 3,계정정보 찾기는 4를 입력하세요"
				+"  목록은 5를 입력하세요");

		a=sc.nextInt();
		
		sc.nextLine();// 숫자 입력후 엔터값 없애기
		
		if(a==1) {
			
			System.out.println("아이디를 입력해주세요");
			id=sc.nextLine();
			
			System.out.println("비밀번호를 입력해주세요");
			pw=sc.nextLine();
			
			
			//객체(Check)의 메소드(login(string id, string pw): int (1:아이디가 틀리다.
			//2. 패스워드 틀렸다. 3:로그인 성공을 통해서 로그인 여부 확인
			
			Check check = new Check(); //객체 만들었음
			int flag = check.login(id,pw); //flag 인수를 통해 check객체에 login메소드 대입하여 로그인 검사 
			
			if(flag==3) {
				System.out.println("로그인 성공");
			} else if(flag==1) {
				System.out.println("없는 아이디입니다. 아이디를 확인하세요");
				System.out.println("로그인 실패");
			} else if(flag==2) {
				System.out.println("비밀번호가 틀립니다. 아이디를 확인하세요");
				System.out.println("로그인 실패");
			}
			
		} else if(a==2) {
			System.out.println("회원가입을 시작합니다.");
			System.out.println("아이디를 입력해주세요");
			id=sc.nextLine();
			System.out.println("비밀번호를 입력해주세요");
			pw=sc.nextLine();
			System.out.println("이름을 입력해주세요");
			name=sc.nextLine();
			
			Check check = new Check(); //객체 만들었음
			check.signUp(id,pw,name);
			
			System.out.println("회원가입 완료");
			
		} else if(a==3) {
			System.out.println("회원삭제를 시작합니다.");
			System.out.println("삭제할 아이디를 입력해주세요");
			id=sc.nextLine();
			System.out.println("삭제할 아이디의 비밀번호를 입력해주세요");
			pw=sc.nextLine();
			
			Check check = new Check();
			check.checkid(id,pw);
			
			int flag = check.checkid(id,pw);
			
			if(flag==3) {
			check.del(id);
			}
			else if(flag==1) {
			System.out.println("그런 아이디는 없습니다");
			}
			else if(flag==2) {
			System.out.println("비밀번호가 틀립니다. 비밀번호를 확인하세요");
			}
			
			
		}
		 else if(a==4) {
				System.out.println("아이디를 찾으시려면 1, 비밀번호를 찾으시려면 2를 입력하세요");
				help=sc.nextInt();
				sc.nextLine(); 
				Check check = new Check();
				
				int flag=0;

				if(help==1) {//id를 찾는 경우
                    System.out.println("회원가입시 입력한 이름을 입력하세요");
        			name=sc.nextLine();
					flag=check.helpid(name);//입력받은 id와 db의 id 중 매치되는 것이 있는지 검사
//					System.out.println(flag+"dddd");
					if(flag==1) {//매치되는 데이터가 있다면
						System.out.println("해당 ID로 로그인해주세요");
					}
					else if(flag==2) {//매치되는 데이터가 없다면
						System.out.println("이름을 확인해주세요");
					}
				}else if(help==2) {
                    System.out.println("회원가입시 입력한 아이디를 입력하세요");
                    id=sc.nextLine();
                    System.out.println("회원가입시 입력한 이름을 입력하세요");
                    name=sc.nextLine();
                    flag=check.helppw(id,name);
//					System.out.println(flag+"dddd");
                    
					if(flag==1) {
						System.out.println("해당 PW로 로그인해주세요");
					}
					else {
						System.out.println("입력된 정보의 계정정보가 없습니다");
						System.out.println("입력한 아이디와 이름을 확인하세요");

					}
					
					
				}else {
					System.out.println("1 또는 2를 입력하세요");
				}
		
		 }
		 else if(a==5) {
			Check check = new Check();
			List<User> list=check.list();
			 for(User temp :list) {
				 System.out.println(temp.id+"   ");
				 System.out.println(temp.pw);
			 }
		 }
		
	}
}
