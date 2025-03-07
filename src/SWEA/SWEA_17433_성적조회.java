package SWEA;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class SWEA_17433_성적조회 {

	private final static int CMD_INIT = 100;
	private final static int CMD_ADD = 200;
	private final static int CMD_REMOVE = 300;
	private final static int CMD_QUERY = 400;

	private final static UserSolution usersolution = new UserSolution();

	private static void String2Char(char[] buf, String str) {
		for (int k = 0; k < str.length(); ++k)
			buf[k] = str.charAt(k);
		buf[str.length()] = '\0';
	}
	private static boolean run(BufferedReader br) throws Exception {
		int q = Integer.parseInt(br.readLine());
		int id, grade, score;
		int cmd, ans, ret;
		boolean okay = false;

		for (int i = 0; i < q; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
				case CMD_INIT:
					usersolution.init();
					okay = true;
					break;
				case CMD_ADD:
					char[] gender = new char[7];
					id = Integer.parseInt(st.nextToken());
					grade = Integer.parseInt(st.nextToken());
					String2Char(gender, st.nextToken());
					score = Integer.parseInt(st.nextToken());
					ans = Integer.parseInt(st.nextToken());
					ret = usersolution.add(id, grade, gender, score);
					if (ret != ans)
						okay = false;
					break;
				case CMD_REMOVE:
					id = Integer.parseInt(st.nextToken());
					ans = Integer.parseInt(st.nextToken());
					ret = usersolution.remove(id);
					if (ret != ans)
						okay = false;
					break;
				case CMD_QUERY:
					int gradeCnt, genderCnt;
					int[] gradeArr = new int[3];
					char[][] genderArr = new char[2][7];
					gradeCnt = Integer.parseInt(st.nextToken());
					for (int j = 0; j < gradeCnt; ++j) {
						gradeArr[j] = Integer.parseInt(st.nextToken());
					}
					genderCnt = Integer.parseInt(st.nextToken());
					for (int j = 0; j < genderCnt; ++j) {
						String2Char(genderArr[j], st.nextToken());
					}
					score = Integer.parseInt(st.nextToken());
					ans = Integer.parseInt(st.nextToken());
					ret = usersolution.query(gradeCnt, gradeArr, genderCnt, genderArr, score);
					if (ret != ans)
						okay = false;
					break;
				default:
					okay = false;
					break;
			}
		}
		return okay;
	}

	public static void main(String[] args) throws Exception {
		int TC, MARK;

		System.setIn(new java.io.FileInputStream("src\\SWEA\\sample_input.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase) {
			int score = run(br) ? MARK : 0;
			System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
}


class UserSolution {

    static HashMap<Integer,information> studentId;
    static TreeSet<Student>[][] students;

	public void init() {
        studentId = new HashMap<>();
        students = new TreeSet[3][2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                students[i][j] = new TreeSet<>();
            }
        }
		return;
	}

	public int add(int mId, int mGrade, char mGender[], int mScore) {
		
        int gender = mGender[0]=='m'?0:1;
        studentId.put(mId, new information(mGrade-1, gender,mScore));
        students[mGrade-1][gender].add(new Student(mId, mScore));
		int ans = students[mGrade-1][gender].last().id;
		//System.out.println(ans);
		return ans;
	}

	public int remove(int mId) {
		if (!studentId.containsKey(mId)){
			//System.out.println(0);
			return 0;
		}
        int grade = studentId.get(mId).grade;
        int gender = studentId.get(mId).gender;
        int score = studentId.get(mId).score;
        studentId.remove(mId);
        students[grade][gender].remove(new Student(mId, score));
        if (!students[grade][gender].isEmpty()){
			int num = students[grade][gender].first().id; 
			//System.out.println(num);
			return num;
		} 
		//System.out.println(0);
        return 0;
	}

	public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
        TreeSet<Student> ts = new TreeSet<>();
		for (int i = 0; i < mGradeCnt; i++) {
			int grade = mGrade[i];
			for (int j = 0; j < mGenderCnt; j++) {
				char[] cs = mGender[j];
				int gender = cs[0]=='m'?0:1;
                Student result = students[grade-1][gender].ceiling(new Student(0,mScore));
                if (result == null)continue;
                ts.add(result);
			}

		}
        if (!ts.isEmpty()){
			//System.out.println(ts.first().id);
			return ts.first().id;
		}
		//System.out.println(0);
		return 0;
	}

    static class Student implements Comparable<Student>{
        int id, score;
        Student(int id, int score){
            this.id = id;
            this.score = score;
        }
        @Override
        public int compareTo(Student o) {
            if (this.score == o.score) {
                return Integer.compare(this.id, o.id);
            }
            return Integer.compare(this.score, o.score);
        }
    }
    static class information{
        int grade; int gender; int score;
        information(int grade, int gender, int score){
            this.grade = grade; this.gender = gender; this.score = score;
        }
    }
}