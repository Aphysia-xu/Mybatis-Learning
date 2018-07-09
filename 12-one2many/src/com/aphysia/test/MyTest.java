package com.aphysia.test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Sides;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aphysia.beans.Student;
import com.aphysia.dao.IStudentDao;
import com.aphysia.utils.MyBatisUtils;

public class MyTest {
	private IStudentDao dao;
	private SqlSession sqlSession;
	@Before
	public void Before(){
		sqlSession=MyBatisUtils.getSqlSession();
		dao=sqlSession.getMapper(IStudentDao.class);
	}
	/**
	 * �������
	 */
	@Test
	public void testInsert(){
		/**
		 * Ҫ��û��select id�������ǲ����Զ���ȡid�ģ�id��һֱΪ��
		 */
		Student student=new Student("enasden",21,94.6);
		System.out.println("����ǰ��student="+student);
		dao.insertStudent(student);
		System.out.println("�����student="+student);
		/**
		 * ��֪��ΪʲôsqlSession.commit����ִ��Ҳ�����ύ�����ݿ�
		 */
		sqlSession.commit();
	}
	/**
	 * ���Բ�����ȡid
	 */
	@Test
	public void testinsertStudentCacheId(){
		Student student=new Student("helloworld",14,94.6);
		System.out.println("����ǰ��student="+student);
		dao.insertStudentCacheId(student);;
		System.out.println("�����student="+student);
	}
	/*
	 * ����ɾ��
	 * 
	 */
	@Test
	public void testdeleteStudentById(){
		dao.deleteStudentById(20);

	}
	/*
	 * �����޸ģ�һ������ҵ�����治����д��һ���ǲ�ѯ����student���޸�
	 * 
	 */
	@Test
	public void testUpdate(){
		Student student=new Student("lallalalla",14,94.6);
		student.setId(8);
		dao.updateStudent(student);

	}
	/*
	 * ��ѯ�б�
	 * 
	 */
	@Test
	public void testselectList(){
		List<Student>students=dao.selectAllStudents();
		if(students.size()>0){
			for(Student student:students){
				System.out.println(student);
			}
		}
	}
	/*
	 * ��ѯ�б�װ��map
	 * ��̬�������治���ã���Ϊmapper����û��idΪselectAllStudentsMap������
	 */
/*	@Test
	public void testselectMap(){
		Map<String,Object>students=dao.selectAllStudentsMap();
		//����ͬ�����ֵĻ�ֱ���滻��֮ǰ������ģ���Ϊ��ͬһ��key
		System.out.println(students.get("helloworld"));
		System.out.println(students.get("1ADAS"));
	}*/
	/*
	 * ͨ��id����ѯstudent
	 * 
	 */
	@Test
	public void testselectStudentById(){
		Student student=dao.selectStudentById(19);
		System.out.println(student);
	}
	/*
	 * ͨ��ģ����ѯstudent������
	 * 
	 */
	@Test
	public void testselectStudentByName(){
		List<Student>students=dao.selectStudentsByName("abc");
		if(students.size()>0){
			for(Student student:students)
				System.out.println(student);
		}
		
	}
	
	
	@Test
	public void testselectStudentByNameAndAge(){
		Student stu=new Student("lallal", 1212, 40);
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("nameCon", "abc");
		map.put("ageCon", 14);
		map.put("stu", stu);
		List<Student>students=dao.selectStudentByNameAndAge(map);
		if(students.size()>0){
			for(Student student:students)
				System.out.println(student);
		}
		
	}
	@After
	public void after(){
		if(sqlSession!=null){
			sqlSession.close();
		}
	}
	
}
