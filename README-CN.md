# jc-common
## 简介
一个包含一些工具类的基础模块

## 怎么使用
### 步骤1

在build.gradle添加repositories

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
### 步骤2

添加依赖

	dependencies {
	        implementation 'com.github.zerochen2016:jc-common:{VERSION}'
	}

你可以在 https://jitpack.io 通过搜索 https://github.com/zerochen2016/jc-common.git 来查看版本号
