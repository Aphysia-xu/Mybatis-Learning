import bean.Student;
import dao.IStudentDao;
import dao.StudentDaoImpl;
import org.junit.Before;
import org.junit.Test;

public class MyTest {
	private IStudentDao dao;
	@Before
	public void Before(){
		dao=new StudentDaoImpl();
	}
	@Test
	public void testInsert(){
		Student student=new Student("test03",24,86);
		dao.insertStu(student);
	}
}
