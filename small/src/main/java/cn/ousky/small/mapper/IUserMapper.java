package cn.ousky.small.mapper;

import cn.ousky.small.entity.User;
import cn.ousky.small.model.RegInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * IUser表apper
 */
@Mapper
public interface IUserMapper {

    /**
     * 通过字段phone查找User信息
     *
     * @param info 用户基本信息
     * @return User
     */
    @Select("<script>" +
            "select * from User where phone=#{phone} " +
            "<if test='pwd!=null'> " +
            "and pwd=#{pwd}" +
            "</if>" +
            "</script>")
    User findByPhone(RegInfo info);


    /**
     * 注册
     *
     * @param info 用户基本信息
     * @return int
     */
    @Select("insert into User(Pwd,Name,Phone) values(#{pwd},#{name},#{phone})")
    int insertReg(RegInfo info);


    /**
     * 更改密码
     *
     * @param info 用户基本信息
     * @return int
     */
    @Update("update User set pwd=#{pwd} where phone=#{phone}")
    int updatePwd(RegInfo info);
}