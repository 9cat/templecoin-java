del  T:\temple\TPC\TPCAndroidWallet\Android\libs\megacoinj-0.11.jar
del  T:\temple\TPC\TPCAndroidWallet\templecoinj\core\target\megacoinj-0.11.jar
call mvn -T 1.5C clean package -Dmaven.test.skip=true 
copy T:\temple\TPC\TPCAndroidWallet\templecoinj\core\target\megacoinj-0.11.jar T:\temple\TPC\TPCAndroidWallet\Android\libs\megacoinj-0.11.jar
timeout 300