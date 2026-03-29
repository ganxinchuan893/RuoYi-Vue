# 本地开发环境指南

## 环境要求

| 工具 | 版本 | 安装方式 |
|------|------|----------|
| Java | 17 | `brew install openjdk@17` |
| Maven | 3.9+ | `brew install maven` |
| Docker | 任意 | [docker.com](https://www.docker.com) |
| Node.js | 16+ | `brew install node` |

安装 Java 后配置环境变量（写入 `~/.zshrc`）：

```bash
export JAVA_HOME=/opt/homebrew/opt/openjdk@17
export PATH="$JAVA_HOME/bin:$PATH"
```

---

## 一、启动基础服务（MySQL + Redis）

```bash
cd ~/Desktop/RuoYi-Vue
docker compose up -d
```

停止：

```bash
docker compose stop
```

---

## 二、启动后端

```bash
cd ~/Desktop/RuoYi-Vue/ruoyi-admin
JAVA_HOME=/opt/homebrew/opt/openjdk@17 mvn spring-boot:run
```

启动成功后终端会显示：

```
(♥◠‿◠)ﾉﾞ  若依启动成功
```

后端地址：`http://localhost:8080`

### 热重载（修改代码后触发）

另开一个终端，执行：

```bash
cd ~/Desktop/RuoYi-Vue
JAVA_HOME=/opt/homebrew/opt/openjdk@17 mvn compile -pl ruoyi-admin -am -q
```

Spring Boot DevTools 会自动检测到 class 文件变化并重启应用。

---

## 三、启动前端

```bash
cd ~/Desktop/RuoYi-Vue/ruoyi-ui
npm install       # 首次运行需要安装依赖
npm run dev
```

前端地址：`http://localhost:80`（代理转发到后端 `localhost:8080`）

---

## 四、服务汇总

| 服务 | 地址 | 启动方式 |
|------|------|----------|
| 后端 API | `http://localhost:8080` | Maven |
| 前端页面 | `http://localhost:80` | npm |
| MySQL | `localhost:3306` | Docker |
| Redis | `localhost:6379` | Docker |
| Swagger 文档 | `http://localhost:8080/swagger-ui.html` | 随后端启动 |
| Druid 监控 | `http://localhost:8080/druid` | 随后端启动，账号 `ruoyi` / `123456` |

---

## 五、数据库信息

| 项目 | 值 |
|------|----|
| 地址 | `localhost:3306` |
| 数据库 | `ry-vue` |
| 用户名 | `root` |
| 密码 | `password` |

> 数据库由 Docker 自动初始化，SQL 脚本位于 `sql/` 目录。  
> 如需重置数据库：`docker compose down -v && docker compose up -d`
