/*
 * �ӿ��๹�캯��,����2�����ϵĲ��������е�һ������Ϊ�ӿ���������Ĳ�������Ϊ�ַ������飬Ҳ����Ϊ�ַ���
 * @param {Object} name
 * �ӿ���
 * @param {Object} methods
 * �ӿڰ����ķ�������,��������Ϊ���飬Ҳ���Դ����������ַ�����ʽ�ķ�����
 */
var Interface = function(name, methods){
	if(arguments.length < 2){ //������������Ϊ2�����׳�����
		throw new Error("Interface constructor called with" + arguments.length + 
			"arguments, but expected at least 2");
	}
	
	this.name = name;
	this.methods = [];
	
	for(var i = 1, len = arguments.length; i < len; ++i){
		if(arguments[i] instanceof Array){ //������Ϊ���飬������ò���
			for(var j = arguments[i].length - 1; j > -1; --j){
				if(typeof arguments[i][j] !== 'string' ){//��֤����ķ�����Ϊ�ַ����������׳�����
					throw new Error('Interface constructor expects method names to be passed in as a string');
				}
				
				this.methods.push(arguments[i][j]); //���淽����
			}
		} else if(typeof arguments[i] === 'string'){ //����Ϊ�ַ�����ֱ�ӱ���
			this.methods.push(arguments[i]);
		} else { //�����׳�����
			throw new Error('Interface constructor expects method names to be passed in as a string');
		}
	}
};

/*
 * �ӿ�ʵ�ּ��麯������һ������ΪҪ���Ķ��󣬺�����������Ϊʵ�ֵĽӿڶ���,Ҳ����Ϊ�ӿڶ�������
 * @param {Object} object
 */
Interface.ensureImplents = function(object){
	if(arguments.length < 2){
		throw new Error("Interface constructor called with" + arguments.length + 
			"arguments, but expected at least 2");
	}
	
	var _checkMethods = function(inface){ //�ڲ����������ڼ�������Ƿ�ʵ����ifs�����еķ���
		var methods = inface.methods,
		          i = methods.length - 1;
			
		for( ; i > -1; --i){
			var method = methods[i];
			//�����󲻴��ڸ����ԣ����߸����Բ��Ƿ�������ô�׳�����
			if(typeof object[method] === 'undefined' || typeof object[method] !== 'function'){
				throw new Error("Function Interface.ensureImplents: object does not implent the " + 
					inface.name + "interface. Method " + method + " was not found."	);
			}
		}
	};
	
	
	for (var i = arguments.length - 1; i > 0; --i) {
		if(arguments[i] instanceof Array){
			for(var j = arguments[i].length - 1; j > -1; --j){
				if(!arguments[i][j] instanceof Interface){
					throw new Error('Function Interface.ensureImplents expects arguments two and above to be' +
						'instances of Interface');
				}
				_checkMethods(arguments[i][j]); //����ӿ�ʵ��
			}
		} else if(arguments[i] instanceof Interface){
			_checkMethods(arguments[i]); //����ӿ�ʵ��
		} else {
			throw new Error('Function Interface.ensureImplents expects arguments two and above to be' +
				'instances of Interface');
		}
	}
};
