#!/usr/bin/env bash

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

echo '1. 显示 9999 进程'
listPort

echo '2. 结束 9999 进程'
cmd=$(lsof -i:9999|grep -E "java" | awk '{print $2}')
echo
echo '请检查上边所列进程的 PID 是否是下边所列 PID？？？'
echo '请检查上边所列进程的 PID 是否是下边所列 PID？？？'
echo '请检查上边所列进程的 PID 是否是下边所列 PID？？？'
echo
echo -e "待结束进程 PID： \n\033[31m\033[05m$cmd\033[0m"
echo
echo '1. 输入y/Y，将自动使用 kill -9 结束进程；'
echo '2. 结束命令；'
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

echo '3. 进入目录'
cd /var/www/sixlab_config/spring/;

echo '4. 备份之前的日志'
t=$(date +%Y-%m-%d.%H%M%S)
mv nohup.out log/${t}.log

echo '5. 移动之前的 jar 包'
mv mbx.jar backup/${t}.jar

echo '6. 移动新的 jar 包'
cp /var/www/code_repo/MineboatX/mbx-web/target/mbx.jar ./mbx.jar

echo '7. 启动服务器'
nohup java -jar mbx.jar &

echo '8. 开始看日志'
tail -f nohup.out