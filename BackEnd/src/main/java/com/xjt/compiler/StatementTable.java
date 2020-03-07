package com.xjt.compiler;
import java.util.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StatementTable{
	ArrayList List = new ArrayList();
	static public String outPut = "";
	public String functions = "";
	public Token whileLeft = new Token();
	public Token whileRight = new Token();
	public Token sign = new Token();
	public boolean isLoop = false;
	static public JSONArray paraArray = new JSONArray();
	static public Map<String, Object> map = new HashMap<String, Object>();
	static public Map<String, ArrayList> arraymap = new HashMap<String, ArrayList>();
	public void addtoArray(Token type, Token state, boolean isTrue, boolean isLoop) throws DeclarationException {
		if (isLoop) {
			functions += "addtoArray\n";
			ArrayList subList = new ArrayList();
			subList.add("addtoArray");
			subList.add(type);
			subList.add(state);
			subList.add(isTrue);
			subList.add(false);
			List.add(subList);
		}
		if (!isTrue) {
			return;
		}
		if (!map.containsKey(state.image) && type.image.equals("int")) {
			map.put(state.image, 0);
		} else if (!map.containsKey(state.image) && type.image.equals("real")) {
			map.put(state.image, 0.0);
		} else {
			throw new DeclarationException(state,1);
		}
	}

	// ��������ĺ�������ÿ������洢��image+array�ĸ�ʽ,����arraymap�У�array�ĵ�һ��洢�������ͣ�0Ϊint��1Ϊdouble��
	public void addtoArraylist(Token type, Token state, boolean isTrue, boolean isLoop) throws DeclarationException {
		if (isLoop) {
			functions += "addtoArrayList\n";
			ArrayList subList = new ArrayList();
			subList.add("addtoArray");
			subList.add(type);
			subList.add(state);
			subList.add(isTrue);
			subList.add(false);
			List.add(subList);
		}
		if (!isTrue) {
			return;
		}
		if (!map.containsKey(state.image) && !arraymap.containsKey(state.image) && type.image.equals("int")) {
			ArrayList list = new ArrayList();
			list.add(0, 0);
			arraymap.put(state.image, list);
		} else if (!map.containsKey(state.image) && !arraymap.containsKey(state.image) && type.image.equals("real")) {
			ArrayList list = new ArrayList();
			list.add(0, 1);
			arraymap.put(state.image, list);
		} else {
			throw new DeclarationException(state,1);
		}
	}

	public void assign(Token state, Token i, Token token, boolean isTrue, boolean isLoop) throws DeclarationException, AssignmentException {
		if (isLoop) {
			functions += "assign\n";
			ArrayList subList = new ArrayList();
			subList.add("assign");
			subList.add(state);
			subList.add(i);
			subList.add(token);
			subList.add(isTrue);
			subList.add(false);
			List.add(subList);
		}
		if (!isTrue) {
			return;
		}
		if (i.image == null) {
			if (map.containsKey(state.image)) {
				if (map.containsKey(token.image)) {					
					map.put(state.image, token.getValue());
				} else if (token.image.matches("^[0-9]*([\\.]{0,1}[0-9]*)$")) {
					map.put(state.image, token.getValue());
				}else if(arraymap.containsKey(token.image)) {
					throw new AssignmentException(token);
				} else {
					throw new DeclarationException(token);
				}
			} else if(arraymap.containsKey(state.image)){
				throw new AssignmentException(state);
			}
			else {throw new DeclarationException(state);}
		} else {
			if(i.image.matches("^[1-9]\\d*|0$")) {
				i.value = Integer.parseInt(i.image);
			}
			else if(map.containsKey(i.image)) {
				i.value = map.get(i.image);
			}
			else {System.out.println("����");}
			if (arraymap.containsKey(state.image)) {
				ArrayList list = arraymap.get(state.image);
				if (map.containsKey(token.image)) {
					token.value = map.get(token.image);
					if (list.size() <= (int) i.value + 1) {
						if ((int) list.get(0) == 0 && token.getValue() instanceof Integer) {
							list.add((int) i.value + 1, token.getValue());
							arraymap.put(state.image, list);
						} else if ((int) list.get(0) == 1 && token.getValue() instanceof Double) {
							list.add((int) i.value + 1, token.getValue());
							arraymap.put(state.image, list);
						} else {
							throw new AssignmentException(state,1);
						}
					} else {
						if ((int) list.get(0) == 0 && token.getValue() instanceof Integer) {
							list.set((int) i.value + 1, token.getValue());
							arraymap.put(state.image, list);
						} else if ((int) list.get(0) == 1 && token.getValue() instanceof Double) {
							list.set((int) i.value + 1, token.getValue());
							arraymap.put(state.image, list);
						} else {
							throw new AssignmentException(state,1);
						}
					}
				} else if (token.image.matches("^[0-9]*([\\.]{0,1}[0-9]*)$")) {
					if (list.size() <= (int) i.value + 1) {
						if ((int) list.get(0) == 0 && token.getValue() instanceof Integer) {
							list.add((int) i.value + 1, token.getValue());
							arraymap.put(state.image, list);
						} else if ((int) list.get(0) == 1 && token.getValue() instanceof Double) {
							list.add((int) i.value + 1, token.getValue());
							arraymap.put(state.image, list);
						} else {
							throw new AssignmentException(state,1);
						}
					} else {
						if ((int) list.get(0) == 0 && token.getValue() instanceof Integer) {
							list.set((int) i.value + 1, token.getValue());
							arraymap.put(state.image, list);
						} else if ((int) list.get(0) == 1 && token.getValue() instanceof Double) {
							list.set((int) i.value + 1, token.getValue());
							arraymap.put(state.image, list);
						} else {
							throw new AssignmentException(state,1);
						}
					}
				} else {
					throw new DeclarationException(token);
				}
			} else {
				throw new AssignmentException(state);
			}
		}
	}

	public void read(Object key, boolean isTrue, boolean isLoop) {
		if (isLoop) {
			functions += "write\n";
			ArrayList subList = new ArrayList();
			subList.add("write");
			subList.add(key);
			subList.add(isTrue);
			subList.add(false);
			List.add(subList);
		}
		if (!isTrue) {
			return;
		}
		if (key instanceof Token) {
			Token newKey = (Token) key;
			if (map.containsKey(newKey.image)) {
				for(int i=0;i<paraArray.size();i++){
					//3、把里面的对象转化为JSONObject
					JSONObject job = paraArray.getJSONObject(i);
					String para1 = job.keys().next().toString();
					if(para1.equals(newKey.image)){
						String para2 = job.get(para1).toString();
						map.put(para1, para2);
						break;
					}
				}
			}

		}
	}

	public void write(Object key, Token i, boolean isTrue, boolean isLoop) throws DeclarationException {
		if (isLoop) {
			functions += "write\n";
			ArrayList subList = new ArrayList();
			subList.add("write");
			subList.add(key);
			subList.add(i);
			subList.add(isTrue);
			subList.add(false);
			List.add(subList);
		}
		if (!isTrue) {
			return;
		}
		Token newKey = (Token) key;
		if (i.image == null) {
			if (map.containsKey(newKey.image)) {
				outPut += "        " + map.get(newKey.image) + "\n";
				System.out.println(map.get(newKey.image));
			} else if (arraymap.containsKey(newKey.image)) {
				ArrayList L = new ArrayList();
				for (int j = 1; j <= arraymap.get(newKey.image).size() - 1; j++) {
					L.add(arraymap.get(newKey.image).get(j));
				}
				outPut += "        " +  "[";
				for (int a= 0;a<L.size();a++) {
					if(a!=L.size()-1) {
						outPut += L.get(a) + ", ";
					}else {
						outPut += L.get(a);
					}
				}
				outPut += "] \n";
				System.out.println(L);
			} else if (newKey.image.matches("^[0-9]*([\\.]{0,1}[0-9]*)$")) {
				outPut +=  "        " + newKey.value + "\n";
				System.out.println(newKey.value);
			} else {
				throw new DeclarationException(newKey);
			}
		} else {
			if (i.image.matches("^[1-9]\\d*|0$")) {
				i.value = Integer.parseInt(i.image);
			} else if (map.containsKey(i.image)) {
				i.value = map.get(i.image);
			}
			if (arraymap.containsKey(newKey.image)) {
				outPut +=  "        " + arraymap.get(newKey.image).get((int) i.value + 1) + "\n";
				System.out.println(arraymap.get(newKey.image).get((int) i.value + 1));
			} else {
				throw new DeclarationException(newKey);
			}
		}

	}

	public boolean condition(Token left, Token sign, Token right, boolean isTrue, boolean isLoop) throws DeclarationException {
		{
			if (isLoop) {
				whileLeft = left;
				whileRight = right;
				this.sign = sign;
			}
			Object a = getValue(left, isTrue, isLoop);
			Object b = getValue(right, isTrue, isLoop);
			switch (sign.image) {
			case ">":
				if (a instanceof Double && b instanceof Double) {
					if ((double) a > (double) b)
						return true;
					return false;
				} else if (a instanceof Double && b instanceof Integer) {
					if ((double) a > (int) b)
						return true;
					return false;
				} else if (a instanceof Integer && b instanceof Double) {
					if ((int) a > (double) b)
						return true;
					return false;
				} else if (a instanceof Integer && b instanceof Integer) {
					if ((int) a > (int) b)
						return true;
					return false;
				} else {
					System.out.println("����ת������");
				}
				break;
			case "<":
				if (a instanceof Double && b instanceof Double) {
					if ((double) a < (double) b)
						return true;
					return false;
				} else if (a instanceof Double && b instanceof Integer) {
					if ((double) a < (int) b)
						return true;
					return false;
				} else if (a instanceof Integer && b instanceof Double) {
					if ((int) a < (double) b)
						return true;
					return false;
				} else if (a instanceof Integer && b instanceof Integer) {
					if ((int) a < (int) b)
						return true;
					return false;
				} else {
					System.out.println("����ת������");
				}
				break;
			case "==":
				if (a instanceof Double && b instanceof Double) {
					if ((double) a == (double) b)
						return true;
					return false;
				} else if (a instanceof Double && b instanceof Integer) {
					if ((double) a == (int) b)
						return true;
					return false;
				} else if (a instanceof Integer && b instanceof Double) {
					if ((int) a == (double) b)
						return true;
					return false;
				} else if (a instanceof Integer && b instanceof Integer) {
					if ((int) a == (int) b)
						return true;
					return false;
				} else {
					System.out.println("����ת������");
				}
				break;
			case "<>":
				if (a instanceof Double && b instanceof Double) {
					if ((double) a != (double) b)
						return true;
					return false;
				} else if (a instanceof Double && b instanceof Integer) {
					if ((double) a != (int) b)
						return true;
					return false;
				} else if (a instanceof Integer && b instanceof Double) {
					if ((int) a != (double) b)
						return true;
					return false;
				} else if (a instanceof Integer && b instanceof Integer) {
					if ((int) a != (int) b)
						return true;
					return false;
				} else {
					System.out.println("����ת������");
				}
				break;
			case ">=":
				if (a instanceof Double && b instanceof Double) {
					if ((double) a >= (double) b)
						return true;
					return false;
				} else if (a instanceof Double && b instanceof Integer) {
					if ((double) a >= (int) b)
						return true;
					return false;
				} else if (a instanceof Integer && b instanceof Double) {
					if ((int) a >= (double) b)
						return true;
					return false;
				} else if (a instanceof Integer && b instanceof Integer) {
					if ((int) a >= (int) b)
						return true;
					return false;
				} else {
					System.out.println("����ת������");
				}
				break;
			case "<=":
				if (a instanceof Double && b instanceof Double) {
					if ((double) a <= (double) b)
						return true;
					return false;
				} else if (a instanceof Double && b instanceof Integer) {
					if ((double) a <= (int) b)
						return true;
					return false;
				} else if (a instanceof Integer && b instanceof Double) {
					if ((int) a <= (double) b)
						return true;
					return false;
				} else if (a instanceof Integer && b instanceof Integer) {
					if ((int) a <= (int) b)
						return true;
					return false;
				} else {
					System.out.println("����ת������");
				}
				break;
			}
			return false;
		}
	}

	public Object getValue(Token state, boolean isTrue, boolean isLoop) throws DeclarationException {
		if (isLoop) {
			functions += "getValue\n";
			ArrayList subList = new ArrayList();
			subList.add("getValue");
			subList.add(state);
			subList.add(isTrue);
			subList.add(false);
			List.add(subList);
		}
		if (map.containsKey(state.image)) {
			return map.get(state.image);
		} else if (state.image.matches("^[0-9]*([\\.]{0,1}[0-9]*)$")) {
			return state.getValue();
		} else {
			throw new DeclarationException(state);
		}
	}

	public void plus(Token d1, Token d2, boolean isTrue, boolean isLoop) {
		if (!isTrue) {
			return;
		}
		if (isLoop) {
			functions += "plus\n";
			ArrayList subList = new ArrayList();
			subList.add("plus");
			subList.add(d1);
			subList.add(d2);
			subList.add(isTrue);
			subList.add(false);
			List.add(subList);
		}
		Object a = d1.value;
		Object b = d2.value;
		if (a instanceof Integer && b instanceof Integer) {
			d1.value = (int) a + (int) b;
		} else if (a instanceof Integer && b instanceof Double) {
			d1.value = (int) a + (double) b;
		} else if (a instanceof Double && b instanceof Double) {
			d1.value = (double) a + (double) b;
		} else if (a instanceof Double && b instanceof Integer) {
			d1.value = (double) a + (int) b;
		}
	}

	public void minus(Token d1, Token d2, boolean isTrue, boolean isLoop) {
		if (!isTrue) {
			return;
		}
		if (isLoop) {
			functions += "minus\n";
			ArrayList subList = new ArrayList();
			subList.add("minus");
			subList.add(d1);
			subList.add(d2);
			subList.add(isTrue);
			subList.add(false);
			List.add(subList);
		}
		Object a = d1.value;
		Object b = d2.value;
		if (a instanceof Integer && b instanceof Integer) {
			d1.value = (int) a - (int) b;
		} else if (a instanceof Integer && b instanceof Double) {
			d1.value = (int) a - (double) b;
		} else if (a instanceof Double && b instanceof Double) {
			d1.value = (double) a - (double) b;
		} else if (a instanceof Double && b instanceof Integer) {
			d1.value = (double) a - (int) b;
		}
	}

	public void mult(Token d1, Token d2, boolean isTrue, boolean isLoop) {
		if (!isTrue) {
			return;
		}
		if (isLoop) {
			functions += "mult\n";
			ArrayList subList = new ArrayList();
			subList.add("mult");
			subList.add(d1);
			subList.add(d2);
			subList.add(isTrue);
			subList.add(false);
			List.add(subList);
		}
		Object a = d1.value;
		Object b = d2.value;
		if (a instanceof Integer && b instanceof Integer) {
			d1.value = (int) a * (int) b;
		} else if (a instanceof Integer && b instanceof Double) {
			d1.value = (int) a * (double) b;
		} else if (a instanceof Double && b instanceof Double) {
			d1.value = (double) a * (double) b;
		} else if (a instanceof Double && b instanceof Integer) {
			d1.value = (double) a * (int) b;
		}
	}

	public void div(Token d1, Token d2, boolean isTrue, boolean isLoop) {
		if (!isTrue) {
			return;
		}
		if (isLoop) {
			functions += "div\n";
			ArrayList subList = new ArrayList();
			subList.add("div");
			subList.add(d1);
			subList.add(d2);
			subList.add(isTrue);
			subList.add(false);
			List.add(subList);
		}
		Object a = d1.value;
		Object b = d2.value;
		if (a instanceof Integer && b instanceof Integer) {
			d1.value = (int) a / (int) b;
		} else if (a instanceof Integer && b instanceof Double) {
			d1.value = (int) a / (double) b;
		} else if (a instanceof Double && b instanceof Double) {
			d1.value = (double) a / (double) b;
		} else if (a instanceof Double && b instanceof Integer) {
			d1.value = (double) a / (int) b;
		}
	}

	public void startLoop(boolean isTrue, boolean isLoop) throws DeclarationException, AssignmentException {
		if (!isTrue) {
			return;
		}
		switch (sign.image) {
		case ">":
			if (getValue(whileLeft, isTrue, false) instanceof Double
					&& getValue(whileRight, isTrue, false) instanceof Double) {
				while ((double) getValue(whileLeft, isTrue, false) > (double) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Double
					&& getValue(whileRight, isTrue, false) instanceof Integer) {
				while ((double) getValue(whileLeft, isTrue, false) > (int) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Integer
					&& getValue(whileRight, isTrue, false) instanceof Double) {
				while ((int) getValue(whileLeft, isTrue, false) > (double) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Integer
					&& getValue(whileRight, isTrue, false) instanceof Integer) {
				while ((int) getValue(whileLeft, isTrue, false) > (int) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else {
				System.out.println("����ת������");
			}
			break;
		case "<":
			if (getValue(whileLeft, isTrue, false) instanceof Double
					&& getValue(whileRight, isTrue, false) instanceof Double) {
				while ((double) getValue(whileLeft, isTrue, false) < (double) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Double
					&& getValue(whileRight, isTrue, false) instanceof Integer) {
				while ((double) getValue(whileLeft, isTrue, false) < (int) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Integer
					&& getValue(whileRight, isTrue, false) instanceof Double) {
				while ((int) getValue(whileLeft, isTrue, false) < (double) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Integer
					&& getValue(whileRight, isTrue, false) instanceof Integer) {
				while ((int) getValue(whileLeft, isTrue, false) < (int) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else {
				System.out.println("����ת������");
			}
			break;
		case "==":
			if (getValue(whileLeft, isTrue, false) instanceof Double
					&& getValue(whileRight, isTrue, false) instanceof Double) {
				while ((double) getValue(whileLeft, isTrue, false) == (double) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Double
					&& getValue(whileRight, isTrue, false) instanceof Integer) {
				while ((double) getValue(whileLeft, isTrue, false) == (int) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Integer
					&& getValue(whileRight, isTrue, false) instanceof Double) {
				while ((int) getValue(whileLeft, isTrue, false) == (double) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Integer
					&& getValue(whileRight, isTrue, false) instanceof Integer) {
				while ((int) getValue(whileLeft, isTrue, false) == (int) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else {
				System.out.println("����ת������");
			}
			break;
		case "!=":
			if (getValue(whileLeft, isTrue, false) instanceof Double
					&& getValue(whileRight, isTrue, false) instanceof Double) {
				while ((double) getValue(whileLeft, isTrue, false) != (double) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Double
					&& getValue(whileRight, isTrue, false) instanceof Integer) {
				while ((double) getValue(whileLeft, isTrue, false) != (int) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Integer
					&& getValue(whileRight, isTrue, false) instanceof Double) {
				while ((int) getValue(whileLeft, isTrue, false) != (double) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Integer
					&& getValue(whileRight, isTrue, false) instanceof Integer) {
				while ((int) getValue(whileLeft, isTrue, false) != (int) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else {
				System.out.println("����ת������");
			}
			break;
		case ">=":
			if (getValue(whileLeft, isTrue, false) instanceof Double
					&& getValue(whileRight, isTrue, false) instanceof Double) {
				while ((double) getValue(whileLeft, isTrue, false) >= (double) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Double
					&& getValue(whileRight, isTrue, false) instanceof Integer) {
				while ((double) getValue(whileLeft, isTrue, false) >= (int) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Integer
					&& getValue(whileRight, isTrue, false) instanceof Double) {
				while ((int) getValue(whileLeft, isTrue, false) >= (double) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Integer
					&& getValue(whileRight, isTrue, false) instanceof Integer) {
				while ((int) getValue(whileLeft, isTrue, false) >= (int) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else {
				System.out.println("����ת������");
			}
			break;
		case "<=":
			if (getValue(whileLeft, isTrue, false) instanceof Double
					&& getValue(whileRight, isTrue, false) instanceof Double) {
				while ((double) getValue(whileLeft, isTrue, false) <= (double) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Double
					&& getValue(whileRight, isTrue, false) instanceof Integer) {
				while ((double) getValue(whileLeft, isTrue, false) <= (int) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Integer
					&& getValue(whileRight, isTrue, false) instanceof Double) {
				while ((int) getValue(whileLeft, isTrue, false) <= (double) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else if (getValue(whileLeft, isTrue, false) instanceof Integer
					&& getValue(whileRight, isTrue, false) instanceof Integer) {
				while ((int) getValue(whileLeft, isTrue, false) <= (int) getValue(whileRight, isTrue, false)) {
					Loop();
				}
			} else {
				System.out.println("����ת������");
			}
			break;
		}
	}

	public void Loop() throws DeclarationException, AssignmentException {
		for (int i = 0; i < List.size(); i++) {
			ArrayList subList = (ArrayList) List.get(i);
			switch ((String) subList.get(0)) {
			case "addtoArray":
					addtoArray((Token) subList.get(1), (Token) subList.get(2), (boolean) subList.get(3),
							(boolean) subList.get(4));
				break;
			case"addtoArrayList":
				addtoArraylist((Token) subList.get(1), (Token) subList.get(2), (boolean) subList.get(3),
						(boolean) subList.get(4));
				break;
			case "assign":
				assign((Token) subList.get(1), (Token) subList.get(2), (Token) subList.get(3), (boolean) subList.get(4),
						(boolean) subList.get(5));
				break;
			case "write":
				write(subList.get(1), (Token) subList.get(2), (boolean) subList.get(3), (boolean) subList.get(4));
				break;
			case "getValue":
				((Token) subList.get(1)).value = getValue((Token) subList.get(1), (boolean) subList.get(2),
						(boolean) subList.get(3));
				break;
			case "plus":
				plus((Token) subList.get(1), (Token) subList.get(2), (boolean) subList.get(3),
						(boolean) subList.get(4));
				break;
			case "minus":
				minus((Token) subList.get(1), (Token) subList.get(2), (boolean) subList.get(3),
						(boolean) subList.get(4));
				break;
			case "mult":
				mult((Token) subList.get(1), (Token) subList.get(2), (boolean) subList.get(3),
						(boolean) subList.get(4));
				break;
			case "div":
				div((Token) subList.get(1), (Token) subList.get(2), (boolean) subList.get(3), (boolean) subList.get(4));
				break;
			}
		}
	}

	public void handleRead(String key, String value) throws DeclarationException{
		map.put(key, value);
		System.out.println(key + value);
	}

	public String getText(){
		return outPut;
	}

	public void reLoad() {
		String errorText = "";
		outPut = "";
		map.clear();
        arraymap.clear();
	}

}
