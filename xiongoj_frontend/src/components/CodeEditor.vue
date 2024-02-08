<template>
  <div id="code-editor" ref="codeEditorRef" style="min-height: 300px;height: 50vh" />
  <!-- <a-button @click="fillValue">填充值</a-button> -->
</template>

<script setup lang="ts">
import * as monaco from "monaco-editor";
import { onMounted, ref, toRaw, watch } from "vue";

/**
 * 定义组件属性类型
 */
interface Props {
  value: string;
  language?:string;
  handleChange: (v: string) => void;
}

/**
 * 给定组件初始值
 */
const props = withDefaults(defineProps<Props>(), {
  value: () => "",
  language:()=>"java",
  handleChange: (v: string) => {
    console.log(v);
  },
});
const codeEditorRef = ref();
const codeEditor = ref();

// const fillValue = () => {
//   if (!codeEditor.value) {
//     return;
//   }
//   // 改变值
//   toRaw(codeEditor.value).setValue("新的值");
// };

// watch([props.language],()=>{
//   codeEditor.value = monaco.editor.create(codeEditorRef.value, {
//     value: props.value,
//     language: props.language,
//     automaticLayout: true,
//     colorDecorators: true,
//     minimap: {
//       enabled: true,
//     },
//     readOnly: false,
//     theme: "vs-dark",
//   });
// })
watch(()=>props.language,()=>{
  if(codeEditor.value){
    monaco.editor.setModelLanguage(
      toRaw(codeEditor.value).getModel(),
      props.language
    );
  }
})
onMounted(() => {
  if (!codeEditorRef.value) {
    return;
  }
  codeEditor.value = monaco.editor.create(codeEditorRef.value, {
    value: props.value,
    language: props.language,
    automaticLayout: true,
    colorDecorators: true,
    minimap: {
      enabled: true,
    },
    readOnly: false,
    theme: "vs-dark",
  });
  // 编辑 监听内容变化
  codeEditor.value.onDidChangeModelContent(() => {
    // console.log("目前内容为：", toRaw(codeEditor.value).getValue());
    props.handleChange(toRaw(codeEditor.value).getValue());
  });
});
</script>

<style>
.bytemd-toolbar-right div:last-child svg {
  display: none !important;
}
</style>
