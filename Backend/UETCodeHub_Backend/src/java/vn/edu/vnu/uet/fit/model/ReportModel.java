/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.vnu.uet.fit.model;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.edu.vnu.uet.fit.entity.report.course.StudentDetailReportByCourse;
import vn.edu.vnu.uet.fit.entity.report.course.StudentReportByCourse;
import vn.edu.vnu.uet.fit.utils.HibernateUtil;

/**
 *
 * @author hmduong
 */
public class ReportModel {

    public List<StudentDetailReportByCourse> getStudentDetailResultByCourse(int courseId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        String queryStr
                = "SELECT C.* FROM \n"
                + "  (SELECT\n"
                + "    @s\\:=@s+1 as reportId, \n"
                + "    courseusers.courseId as courseId, \n"
                + "    courseusers.userId as userId, \n"
                + "    B.problemId as problemId, \n"
                + "    B.submitId as submitId, \n"
                + "    B.result as result, \n"
                + "    B.resultScore as score,\n"
                + "    B.isActive as isActive\n"
                + "  FROM courseusers left outer join \n"
                + "    (SELECT * FROM submissions\n"
                + "    WHERE courseId = 1 and isActive != 0) as B on courseusers.courseId = B.courseId and courseusers.userId = B.userId,\n"
                + "  	 (SELECT @s\\:= 0) AS s\n"
                + "  WHERE courseusers.courseId = :courseId\n"
                + "  ORDER BY userId, problemId, score desc) AS C\n"
                + "  GROUP BY courseId, userId, problemId";
        SQLQuery query = session.createSQLQuery(queryStr);
        query.setInteger("courseId", courseId);
        query.addEntity(StudentDetailReportByCourse.class);
        List<StudentDetailReportByCourse> result = query.list();
        trans.commit();
        session.close();
        return result;
    }

    public List<StudentReportByCourse> getStudentResultByCourse(int courseId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        String queryStr
                = "SELECT @s\\:=@s+1 as reportId, courseId, userId, COALESCE(sum(isActive),0) as summitedExercise, COALESCE(SUM(score), 0) as courseScore FROM\n"
                + "  (SELECT \n"
                + "      courseusers.courseId as courseId, \n"
                + "      courseusers.userId as userId, \n"
                + "      B.problemId as problemId, \n"
                + "      B.submitId as submitId, \n"
                + "      B.result as result, \n"
                + "      B.resultScore as score,\n"
                + "      B.isActive as isActive\n"
                + "    FROM courseusers left outer join (\n"
                + "      select submitId, courseId, problemId, userId, result, max(resultScore) as resultScore, isActive from submissions \n"
                + "        where submissions.courseId is not null and isActive != 0\n"
                + "      GROUP BY courseId, problemId, userId) as B on courseusers.courseId = B.courseId and courseusers.userId = B.userId\n"
                + "  ) as C,\n"
                + "  (SELECT @s\\:= 0) AS s\n"
                + "GROUP BY courseId, userId \n"
                + "HAVING C.courseId = :courseId";
        SQLQuery query = session.createSQLQuery(queryStr);
        query.setInteger("courseId", courseId);
        query.addEntity(StudentReportByCourse.class);
        List<StudentReportByCourse> result = query.list();
        trans.commit();
        session.close();
        return result;
    }
    
    

}
