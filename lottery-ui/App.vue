<script>
	import {
		getSessionList
	} from '@/api/chat.js'
	
	import {
		mapMutations
	} from 'vuex'

	export default {
		onLaunch: function() {
			console.warn('当前组件仅支持 uni_modules 目录结构，请升级 HBuilderX 到 3.1.0 版本以上！')
			console.log('App Launch')
			uni.onTabBarMidButtonTap(() => {
				console.log("点击了");

			});
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		},
		mounted() {


			let timer = null
			timer = setInterval(() => {
				let token = uni.getStorageSync('user_token');
				token && this.getMessage()
			}, 5000)


		},
		methods: {
			...mapMutations(['updateMessage']),
			//定时获取消息数
			async getMessage() {

				let num = 0
				const res = await getSessionList()
				res.data.forEach(item => {
					num += item.unReadCount
				})

				if (num > 99) {
					num = 99
				}


				this.updateMessage(num)
			}
		}
	}
</script>

<style lang="scss">
	@import '@/uni_modules/uni-scss/index.scss';
	@import '@/static/customicons.css';
	/*每个页面公共css */
	@import "./common/uni.scss";
	@import './common/common.scss';
	@import './common/tb.scss';

	.uni-navbar {
		::v-deep .uni-navbar-btn-text {
			text {
				font-size: 15px !important;
			}
		}
	}
	uni-button[type=primary] {
	  color: #ffffff;
	  background-color: #E53E58 !important;
	}
	uni-button[type='primary'][plain] {
	    color: #E53E58 !important;
	    border: 1px solid #E53E58 !important;
	    background-color: transparent !important;
	}
</style>