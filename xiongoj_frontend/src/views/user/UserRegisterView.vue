<template>
  <div class="userLoginView">
    <h2 style="margin-bottom: 16px">用户注册</h2>
    <a-form
      :model="form"
      @submit="handleSubmit"
      label-align="left"
      auto-label-width
      style="max-width: 480px; margin: 0 auto"
    >
      <!-- 账号 -->
      <a-form-item
        :rules="[
          { required: true, message: '账号不能为空' },
          { minLength: 4, message: '账号长度不能低于四位' }
        ]"
        field="userAccount"
        label="账号 :"
      >
        <a-input v-model="form.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <!-- 用户名 -->
      <a-form-item
        :rules="[{ required: true, message: '昵称不能为空' }]"
        field="userName"
        label="昵称 :"
      >
        <a-input v-model="form.userName" placeholder="请输入昵称" />
      </a-form-item>
      <!-- 密码 -->
      <a-form-item
        :rules="[
          { required: true, message: '密码不能为空' },
          { minLength: 8, message: '密码长度不能低于八位' }
        ]"
        field="userPassword"
        tooltip="密码不少于6位"
        label="密码 :"
      >
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入密码"
        />
      </a-form-item>
      <a-form-item
        :rules="[
          { required: true, message: '密码不能为空' },
          { minLength: 8, message: '密码长度不能低于八位' }
        ]"
        field="checkPassword"
        tooltip="密码不少于6位"
        label="重复密码 :"
      >
        <a-input-password
          v-model="form.checkPassword"
          placeholder="请再次输入密码"
        />
      </a-form-item>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="margin: 0 auto">
          注册
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>
<script lang="ts" setup>
import { reactive } from 'vue'
import { UserControllerService, UserRegisterRequest } from '../../../generated'
import { Message } from '@arco-design/web-vue'
import store from '@/store'
import { useRouter } from 'vue-router'
const router = useRouter()
const form = reactive<UserRegisterRequest>({
  userAccount: '',
  userName: '',
  userPassword: '',
  checkPassword: ''
})
const handleSubmit = async () => {
  if (form.userAccount.length < 4 || form.userPassword.length < 6) {
    return
  }
  if (
    form.checkPassword.length !== form.userPassword.length ||
    form.checkPassword !== form.userPassword
  ) {
    Message.error('两次输入密码不一致')
    return
  }
  console.log(form)
  const res = await UserControllerService.userRegisterUsingPost(form)
  if (res.code === 0) {
    Message.success('注册成功！')
    await store.dispatch('user/getLoginUser')
    await router.push({
      path: '/user/login',
      replace: true
    })
  } else {
    Message.error(res.msg)
  }
}
</script>
