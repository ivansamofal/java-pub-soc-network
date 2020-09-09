package hello.dtos;

public class NestedDto {
    private String name;

    public NestedDto(String name) {
        this.name = name;
    }

    public class SomeInnerClass {
        private String some = "some";

        public String method() {
            return this.some.concat(NestedDto.this.name);
        }

        public class MyTwoInnerClass {
            private String test = "test";

            public String methodTest() {
                return this.test.concat(SomeInnerClass.this.some);
            }
        }
    }
}
