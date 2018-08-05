#!/usr/bin/env bash

t=$(date +%Y-%m-%d.%H%M%S)

function listPort(){
    echo '以下为服务器 9999 端口所占用进程，请检查'
    head=$(lsof -i:9999|grep 'PID')
    biao=$(lsof -i:9999|grep 'java')

    if [[ "$head" = "" ]]
    then
        echo '应用列表 无'
    else
        echo "应用列表 $head"
    fi

    if [[ "$biao" = "" ]]
    then
        echo '我的应用 无'
    else
        echo "我的应用 $biao"
    fi
}

echo '现在时间：'${t}

echo '1. 更新代码'
git pull

echo '2. 打包代码'
mvn clean install -Dmaven.test.skip=true 

echo '3. 显示 9999 进程'
listPort

echo '4. 结束 9999 进程'
cmd=$(lsof -i:9999|grep -E "java" | awk '{print $2}')
echo
echo '请检查上边所列进程的 PID 是否是下边所列 PID？？？'
echo '请检查上边所列进程的 PID 是否是下边所列 PID？？？'
echo '请检查上边所列进程的 PID 是否是下边所列 PID？？？'
echo
echo -e "待结束进程 PID： \n\033[31m\033[05m$cmd\033[0m"
echo
echo '1. 输入y/Y，将自动使用 kill -9 结束进程；'
echo '2. 输入n/N，退出发布；'
echo '3. ctrl+c/结束命令，退出发布；'
echo '4. 其他输入，不结束进程，继续发布。'
read -p "请输入后续操作:" choice
if [[ "$choice" = "y" || "$choice" = "Y"  ]]
then
for id in ${cmd}
    do
    kill -9 ${id}
    echo "kill $id"
    done
echo '完成kill'
fi
if [[ "$choice" = "n" || "$choice" = "N"  ]]
then
    exit
fi

echo '5. 进入目录'
cd /var/www/sixlab_config/spring/

echo '6. 备份之前的日志'
mv nohup.out log/${t}.log
touch nohup.out

echo '7. 移动之前的 jar 包'
mv mbx.jar backup/${t}.jar

echo '8. 移动新的 jar 包'
cp /var/www/code_repo/MineboatX/mbx-web/target/mbx.jar ./mbx.jar

echo '9. 启动服务器'
nohup java -jar mbx.jar &

echo '10. 开始看日志'
tail -f nohup.out
