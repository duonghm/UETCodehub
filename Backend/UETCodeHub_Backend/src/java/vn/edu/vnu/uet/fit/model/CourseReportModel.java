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
import vn.edu.vnu.uet.fit.entity.report.course.CourseReportOverall;
import vn.edu.vnu.uet.fit.entity.report.course.StudentDetailReportByCourse;
import vn.edu.vnu.uet.fit.entity.report.course.StudentReportByCourse;
import vn.edu.vnu.uet.fit.utils.HibernateUtil;

/**
 *
 * @author hmduong
 */
public class CourseReportModel {

    public List<CourseReportOverall> getCourseReports() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        String queryStr
                = "select F.courseId as reportId, F.courseId, F.numberOfStudent, F.averageScore ,count(problemId) as numberOfProblem from\n"
                + "  (select courseId, count(userId) as numberOfStudent, avg(totalScore) as averageScore from\n"
                + "    (select courseId, userId, COALESCE(sum(resultScore),0) as totalScore from  \n"
                + "      (select C.courseId, courseusers.userId, submit.problemId, submit.result, submit.resultScore, submit.isActive\n"
                + "        from courseusers \n"
                + "        left join (select submissions.submitId, submissions.courseId, submissions.problemId, submissions.userId, submissions.result, submissions.resultScore, submissions.isActive \n"
                + "          from submissions\n"
                + "            inner join (select courseId, problemId, userId, max(resultScore) as maxscore from submissions where submissions.courseId is not null and isActive != 0 GROUP BY courseId, problemId, userId) as B\n"
                + "            on submissions.courseId = B.courseId and submissions.problemId = B.problemId and submissions.userId = B.userId\n"
                + "          where submissions.resultScore = B.maxscore and isActive != 0) as submit on courseusers.courseId = submit.courseId and courseusers.userId = submit.userId\n"
                + "        right join (select * from courses) as C on courseusers.courseId = C.courseId) as D\n"
                + "      group by courseId, userId) as E\n"
                + "    group by courseId\n"
                + "  )as F left join courseproblems on F.courseId = courseproblems.courseId\n"
                + "  group by F.courseId";
        SQLQuery query = session.createSQLQuery(queryStr);
        query.addEntity(CourseReportOverall.class);
        List<CourseReportOverall> result = query.list();
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

    public List<StudentDetailReportByCourse> getStudentDetailResultByCourse(int courseId, int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        String queryStr
                = "select @s\\:=@s+1 as reportId, submissions.submitId, submissions.courseId, submissions.problemId, submissions.userId, submissions.result, submissions.resultScore, submissions.isActive \n"
                + "  from submissions\n"
                + "    inner join (select courseId, problemId, userId, max(resultScore) as maxscore from submissions where submissions.courseId is not null and isActive != 0 GROUP BY courseId, problemId, userId) as B\n"
                + "    on submissions.courseId = B.courseId and submissions.problemId = B.problemId and submissions.userId = B.userId,\n"
                + "    (SELECT @s\\:= 0) AS s\n"
                + "  where submissions.resultScore = B.maxscore and submissions.isActive != 0 and submissions.userId = :userId and submissions.courseId = :courseId";
        SQLQuery query = session.createSQLQuery(queryStr);
        query.setInteger("courseId", courseId);
        query.setInteger("userId", userId);
        query.addEntity(StudentDetailReportByCourse.class);
        List<StudentDetailReportByCourse> result = query.list();
        trans.commit();
        session.close();
        return result;
    }
}
