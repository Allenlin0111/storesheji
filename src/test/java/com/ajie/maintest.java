package com.ajie;

import org.springframework.boot.test.context.SpringBootTest;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class maintest {

    /*@Autowired
    private WxUserMapper wxUserMapper;

    @Test
    void contextLoads() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String, Object>> byTime = wxUserMapper.findByTime("2020-11-01", format.format(new Date()));
        for (Map<String, Object> map : byTime) {
            String timeData = map.get("timeData").toString();
            String valueData = map.get("valueData").toString();
            System.out.println("时间： "+ timeData + " 增长值： " + valueData);
        }
    }*/

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Test
//    void test2() {
//        System.out.println();
//        String encode = bCryptPasswordEncoder.encode("123456");
//        boolean matches = bCryptPasswordEncoder.matches("123456", encode);
//        System.out.println(matches);
//        System.out.println(encode);
//    }

}
